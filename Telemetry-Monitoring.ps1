# Script de Monitoreo y Telemetría para CAE + Token Protection
# Monitorea sign-in logs, token binding status y alertas de seguridad

param(
    [Parameter(Mandatory=$true)]
    [string]$TenantId,
    
    [Parameter(Mandatory=$true)]
    [string]$ClientId,
    
    [Parameter(Mandatory=$true)]
    [string]$ClientSecret,
    
    [Parameter(Mandatory=$false)]
    [int]$HoursBack = 24
)

# Importar módulos
Import-Module Microsoft.Graph.Identity.SignIns
Import-Module Microsoft.Graph.Security

# Conectar a Microsoft Graph
$body = @{
    client_id = $ClientId
    client_secret = $ClientSecret
    scope = "https://graph.microsoft.com/.default"
    grant_type = "client_credentials"
}

$tokenResponse = Invoke-RestMethod -Uri "https://login.microsoftonline.com/$TenantId/oauth2/v2.0/token" -Method Post -Body $body
$headers = @{
    Authorization = "Bearer $($tokenResponse.access_token)"
    "Content-Type" = "application/json"
}

Write-Host "✅ Conectado a Microsoft Graph para telemetría" -ForegroundColor Green

# 1. MONITOREAR SIGN-IN LOGS
function Get-SignInLogs {
    Write-Host "📊 Analizando sign-in logs..." -ForegroundColor Yellow
    
    $startTime = (Get-Date).AddHours(-$HoursBack).ToString("yyyy-MM-ddTHH:mm:ss.fffZ")
    $endTime = (Get-Date).ToString("yyyy-MM-ddTHH:mm:ss.fffZ")
    
    $filter = "createdDateTime ge $startTime and createdDateTime le $endTime"
    $select = "id,createdDateTime,userPrincipalName,appDisplayName,clientAppUsed,deviceDetail,riskLevelDuringSignIn,conditionalAccessStatus,tokenIssuerType,authenticationRequirement"
    
    $uri = "https://graph.microsoft.com/v1.0/auditLogs/signIns?`$filter=$filter&`$select=$select&`$top=1000"
    
    $signInLogs = Invoke-RestMethod -Uri $uri -Headers $headers
    
    return $signInLogs.value
}

# 2. ANALIZAR TOKEN BINDING STATUS
function Analyze-TokenBinding {
    param($SignInLogs)
    
    Write-Host "🔍 Analizando token binding status..." -ForegroundColor Yellow
    
    $tokenBindingAnalysis = @{
        TotalSignIns = $SignInLogs.Count
        TokenBindingSupported = 0
        TokenBindingFailed = 0
        CAEEnabled = 0
        CAEFailed = 0
        BYODIssues = 0
        MobileAppIssues = 0
    }
    
    foreach ($log in $SignInLogs) {
        # Analizar token binding
        if ($log.tokenIssuerType -eq "AzureAD") {
            $tokenBindingAnalysis.TokenBindingSupported++
        }
        
        # Analizar CAE
        if ($log.authenticationRequirement -eq "multiFactorAuthentication") {
            $tokenBindingAnalysis.CAEEnabled++
        }
        
        # Detectar problemas BYOD
        if ($log.deviceDetail.deviceOwnership -eq "Personal" -and $log.conditionalAccessStatus -ne "success") {
            $tokenBindingAnalysis.BYODIssues++
        }
        
        # Detectar problemas en apps móviles
        if ($log.clientAppUsed -like "*Mobile*" -and $log.conditionalAccessStatus -ne "success") {
            $tokenBindingAnalysis.MobileAppIssues++
        }
    }
    
    return $tokenBindingAnalysis
}

# 3. DETECTAR PROBLEMAS DE CAE
function Detect-CAEIssues {
    param($SignInLogs)
    
    Write-Host "🚨 Detectando problemas de CAE..." -ForegroundColor Yellow
    
    $caeIssues = @()
    
    foreach ($log in $SignInLogs) {
        if ($log.conditionalAccessStatus -eq "failure") {
            $issue = @{
                User = $log.userPrincipalName
                App = $log.appDisplayName
                ClientApp = $log.clientAppUsed
                Device = $log.deviceDetail.deviceOwnership
                RiskLevel = $log.riskLevelDuringSignIn
                Timestamp = $log.createdDateTime
                Issue = "CAE Failure"
            }
            $caeIssues += $issue
        }
    }
    
    return $caeIssues
}

# 4. DETECTAR PROBLEMAS DE TOKEN PROTECTION
function Detect-TokenProtectionIssues {
    param($SignInLogs)
    
    Write-Host "🔒 Detectando problemas de Token Protection..." -ForegroundColor Yellow
    
    $tokenProtectionIssues = @()
    
    foreach ($log in $SignInLogs) {
        # Detectar re-prompts frecuentes (múltiples sign-ins del mismo usuario en corto tiempo)
        $userLogs = $SignInLogs | Where-Object { $_.userPrincipalName -eq $log.userPrincipalName }
        $recentLogs = $userLogs | Where-Object { 
            (Get-Date $_.createdDateTime) -gt (Get-Date $log.createdDateTime).AddMinutes(-30) 
        }
        
        if ($recentLogs.Count -gt 3) {
            $issue = @{
                User = $log.userPrincipalName
                App = $log.appDisplayName
                ClientApp = $log.clientAppUsed
                Device = $log.deviceDetail.deviceOwnership
                SignInCount = $recentLogs.Count
                Timestamp = $log.createdDateTime
                Issue = "Frequent Re-prompts"
            }
            $tokenProtectionIssues += $issue
        }
    }
    
    return $tokenProtectionIssues
}

# 5. GENERAR REPORTE DE COMPLIANCE
function Generate-ComplianceReport {
    param($TokenBindingAnalysis, $CAEIssues, $TokenProtectionIssues)
    
    Write-Host "📋 Generando reporte de compliance..." -ForegroundColor Yellow
    
    $report = @{
        Timestamp = Get-Date
        Summary = $TokenBindingAnalysis
        CAEIssues = $CAEIssues
        TokenProtectionIssues = $TokenProtectionIssues
        Recommendations = @()
    }
    
    # Generar recomendaciones
    if ($TokenBindingAnalysis.BYODIssues -gt 0) {
        $report.Recommendations += "Revisar políticas de Conditional Access para dispositivos BYOD"
    }
    
    if ($TokenBindingAnalysis.MobileAppIssues -gt 0) {
        $report.Recommendations += "Verificar App Protection Policies para aplicaciones móviles"
    }
    
    if ($CAEIssues.Count -gt 0) {
        $report.Recommendations += "Investigar fallos de CAE y ajustar políticas de riesgo"
    }
    
    if ($TokenProtectionIssues.Count -gt 0) {
        $report.Recommendations += "Optimizar Token Protection para reducir re-prompts"
    }
    
    return $report
}

# 6. ENVIAR ALERTAS
function Send-Alerts {
    param($Report)
    
    Write-Host "📧 Enviando alertas..." -ForegroundColor Yellow
    
    $criticalIssues = $Report.CAEIssues.Count + $Report.TokenProtectionIssues.Count
    
    if ($criticalIssues -gt 0) {
        $alertBody = @{
            subject = "🚨 Alertas de Seguridad CAE + Token Protection"
            body = @{
                content = "Se detectaron $criticalIssues problemas críticos en el sistema de seguridad."
                contentType = "Text"
            }
            toRecipients = @(
                @{
                    emailAddress = @{
                        address = "security@company.com"
                    }
                }
            )
        }
        
        $alertJson = $alertBody | ConvertTo-Json -Depth 3
        Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/me/sendMail" -Method Post -Headers $headers -Body $alertJson
        
        Write-Host "✅ Alertas enviadas" -ForegroundColor Green
    }
}

# 7. EXPORTAR DATOS A CSV
function Export-DataToCSV {
    param($SignInLogs, $Report)
    
    Write-Host "💾 Exportando datos a CSV..." -ForegroundColor Yellow
    
    # Exportar sign-in logs
    $SignInLogs | Export-Csv -Path "/workspace/signin-logs-$(Get-Date -Format 'yyyyMMdd-HHmmss').csv" -NoTypeInformation
    
    # Exportar reporte
    $Report | ConvertTo-Json -Depth 3 | Out-File -FilePath "/workspace/compliance-report-$(Get-Date -Format 'yyyyMMdd-HHmmss').json"
    
    Write-Host "✅ Datos exportados" -ForegroundColor Green
}

# EJECUTAR MONITOREO
try {
    Write-Host "🚀 Iniciando monitoreo de CAE + Token Protection..." -ForegroundColor Cyan
    
    $signInLogs = Get-SignInLogs
    $tokenBindingAnalysis = Analyze-TokenBinding -SignInLogs $signInLogs
    $caeIssues = Detect-CAEIssues -SignInLogs $signInLogs
    $tokenProtectionIssues = Detect-TokenProtectionIssues -SignInLogs $signInLogs
    $report = Generate-ComplianceReport -TokenBindingAnalysis $tokenBindingAnalysis -CAEIssues $caeIssues -TokenProtectionIssues $tokenProtectionIssues
    
    # Mostrar resumen
    Write-Host "`n📊 RESUMEN DE MONITOREO" -ForegroundColor Cyan
    Write-Host "Total Sign-ins: $($tokenBindingAnalysis.TotalSignIns)" -ForegroundColor White
    Write-Host "Token Binding Soportado: $($tokenBindingAnalysis.TokenBindingSupported)" -ForegroundColor White
    Write-Host "CAE Habilitado: $($tokenBindingAnalysis.CAEEnabled)" -ForegroundColor White
    Write-Host "Problemas BYOD: $($tokenBindingAnalysis.BYODIssues)" -ForegroundColor White
    Write-Host "Problemas Apps Móviles: $($tokenBindingAnalysis.MobileAppIssues)" -ForegroundColor White
    Write-Host "Issues CAE: $($caeIssues.Count)" -ForegroundColor White
    Write-Host "Issues Token Protection: $($tokenProtectionIssues.Count)" -ForegroundColor White
    
    # Mostrar recomendaciones
    Write-Host "`n💡 RECOMENDACIONES" -ForegroundColor Cyan
    foreach ($recommendation in $report.Recommendations) {
        Write-Host "• $recommendation" -ForegroundColor Yellow
    }
    
    Send-Alerts -Report $report
    Export-DataToCSV -SignInLogs $signInLogs -Report $report
    
    Write-Host "`n✅ Monitoreo completado exitosamente!" -ForegroundColor Green
    
} catch {
    Write-Host "❌ Error en el monitoreo: $($_.Exception.Message)" -ForegroundColor Red
}
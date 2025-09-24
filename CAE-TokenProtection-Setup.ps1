# CAE + Token Protection + BYOD Configuration Script
# Configuración completa para resolver conflictos BYOD

param(
    [Parameter(Mandatory=$true)]
    [string]$TenantId,
    
    [Parameter(Mandatory=$true)]
    [string]$ClientId,
    
    [Parameter(Mandatory=$true)]
    [string]$ClientSecret
)

# Importar módulos necesarios
Import-Module Microsoft.Graph.Identity.ConditionalAccess
Import-Module Microsoft.Graph.Identity.SignIns
Import-Module Microsoft.Graph.Applications

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

Write-Host "✅ Conectado a Microsoft Graph" -ForegroundColor Green

# 1. CONFIGURAR CAE (Continuous Access Evaluation)
function Enable-CAE {
    Write-Host "🔧 Configurando CAE..." -ForegroundColor Yellow
    
    $caeConfig = @{
        isEnabled = $true
        clientAppTypes = @("publicClient", "confidentialClient")
    }
    
    $caeBody = $caeConfig | ConvertTo-Json -Depth 3
    Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/identity/continuousAccessEvaluationPolicy" -Method Patch -Headers $headers -Body $caeBody
    
    Write-Host "✅ CAE habilitado" -ForegroundColor Green
}

# 2. CONFIGURAR TOKEN PROTECTION
function Enable-TokenProtection {
    Write-Host "🔧 Configurando Token Protection..." -ForegroundColor Yellow
    
    $tokenProtectionConfig = @{
        isEnabled = $true
        clientAppTypes = @("publicClient", "confidentialClient")
    }
    
    $tokenProtectionBody = $tokenProtectionConfig | ConvertTo-Json -Depth 3
    Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/identity/tokenProtectionPolicy" -Method Patch -Headers $headers -Body $tokenProtectionBody
    
    Write-Host "✅ Token Protection habilitado" -ForegroundColor Green
}

# 3. CREAR GRUPOS DE USUARIOS
function Create-UserGroups {
    Write-Host "🔧 Creando grupos de usuarios..." -ForegroundColor Yellow
    
    $groups = @(
        @{
            displayName = "BYOD Users"
            description = "Usuarios con dispositivos BYOD"
            mailEnabled = $false
            securityEnabled = $true
        },
        @{
            displayName = "Privileged Roles"
            description = "Usuarios con roles privilegiados"
            mailEnabled = $false
            securityEnabled = $true
        }
    )
    
    foreach ($group in $groups) {
        $groupBody = $group | ConvertTo-Json -Depth 3
        $response = Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/groups" -Method Post -Headers $headers -Body $groupBody
        Write-Host "✅ Grupo creado: $($group.displayName)" -ForegroundColor Green
    }
}

# 4. CONFIGURAR CONDITIONAL ACCESS
function Configure-ConditionalAccess {
    Write-Host "🔧 Configurando Conditional Access..." -ForegroundColor Yellow
    
    # Política para apps soportadas
    $caPolicy1 = @{
        displayName = "CAE + Token Protection - Apps Soportadas"
        description = "Aplica CAE y Token Protection solo a aplicaciones que los soportan"
        state = "enabled"
        conditions = @{
            clientAppTypes = @("publicClient", "confidentialClient")
            applications = @{
                includeApplications = @("00000003-0000-0000-c000-000000000000") # Office 365
                excludeApplications = @("00000002-0000-0ff1-ce00-000000000000") # Legacy Auth
            }
            users = @{
                includeUsers = @("All")
                excludeUsers = @("BYOD Users")
            }
            devices = @{
                deviceFilter = @{
                    mode = "exclude"
                    rule = "device.deviceOwnership -eq `"Personal`""
                }
            }
        }
        grantControls = @{
            operator = "AND"
            builtInControls = @("compliantDevice", "approvedApplication", "applicationEnforcedRestrictions")
        }
        sessionControls = @{
            applicationEnforcedRestrictions = @{
                isEnabled = $true
            }
        }
    }
    
    $caBody1 = $caPolicy1 | ConvertTo-Json -Depth 5
    Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/identity/conditionalAccess/policies" -Method Post -Headers $headers -Body $caBody1
    
    # Política para BYOD
    $caPolicy2 = @{
        displayName = "BYOD - MAM Only"
        description = "Política específica para dispositivos BYOD con MAM"
        state = "enabled"
        conditions = @{
            clientAppTypes = @("publicClient", "confidentialClient")
            applications = @{
                includeApplications = @("00000003-0000-0000-c000-000000000000")
            }
            users = @{
                includeUsers = @("BYOD Users")
            }
            devices = @{
                deviceFilter = @{
                    mode = "include"
                    rule = "device.deviceOwnership -eq `"Personal`""
                }
            }
        }
        grantControls = @{
            operator = "AND"
            builtInControls = @("approvedApplication", "applicationEnforcedRestrictions")
        }
        sessionControls = @{
            signInFrequency = @{
                type = "hours"
                value = 8
            }
            applicationEnforcedRestrictions = @{
                isEnabled = $true
            }
        }
    }
    
    $caBody2 = $caPolicy2 | ConvertTo-Json -Depth 5
    Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/identity/conditionalAccess/policies" -Method Post -Headers $headers -Body $caBody2
    
    Write-Host "✅ Políticas de Conditional Access configuradas" -ForegroundColor Green
}

# 5. CONFIGURAR APP PROTECTION POLICIES
function Configure-AppProtection {
    Write-Host "🔧 Configurando App Protection Policies..." -ForegroundColor Yellow
    
    $appProtectionPolicy = @{
        displayName = "BYOD - Outlook Mobile Protection"
        description = "Política MAM para Outlook móvil en dispositivos BYOD"
        targetedApps = @("com.microsoft.office.outlook")
        dataProtection = @{
            encryptOrgData = "Required"
            encryptOrgDataOnPersonalDevices = "Required"
            preventBackup = "Required"
            preventScreenCapture = "Required"
            restrictCutCopyPaste = "Required"
            restrictDataSharing = "Required"
            restrictDataTransfer = "Required"
        }
        accessRequirements = @{
            requirePin = "Required"
            requireBiometric = "Required"
            requireCorporateCredentials = "Required"
            requireDeviceCompliance = "NotRequired"
        }
        conditionalLaunch = @{
            maxPinAttempts = 5
            offlineGracePeriod = 720
            offlineGracePeriodAction = "Wipe"
            minAppVersion = "16.0.0"
            minOsVersion = "14.0"
            jailbrokenDevice = "Block"
            rootedDevice = "Block"
        }
    }
    
    $appProtectionBody = $appProtectionPolicy | ConvertTo-Json -Depth 5
    Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/deviceAppManagement/managedAppPolicies" -Method Post -Headers $headers -Body $appProtectionBody
    
    Write-Host "✅ App Protection Policies configuradas" -ForegroundColor Green
}

# 6. CONFIGURAR TELEMETRÍA
function Configure-Telemetry {
    Write-Host "🔧 Configurando telemetría..." -ForegroundColor Yellow
    
    # Configurar alertas de sign-in logs
    $alertConfig = @{
        displayName = "CAE Token Protection Alerts"
        description = "Alertas para problemas de CAE y Token Protection"
        conditions = @{
            clientAppTypes = @("publicClient", "confidentialClient")
            riskLevels = @("high", "medium")
        }
        actions = @{
            notificationChannels = @("email", "teams")
            recipients = @("security@company.com")
        }
    }
    
    $alertBody = $alertConfig | ConvertTo-Json -Depth 3
    Invoke-RestMethod -Uri "https://graph.microsoft.com/v1.0/identity/conditionalAccess/policies" -Method Post -Headers $headers -Body $alertBody
    
    Write-Host "✅ Telemetría configurada" -ForegroundColor Green
}

# EJECUTAR CONFIGURACIÓN
try {
    Enable-CAE
    Enable-TokenProtection
    Create-UserGroups
    Configure-ConditionalAccess
    Configure-AppProtection
    Configure-Telemetry
    
    Write-Host "🎉 Configuración completa finalizada!" -ForegroundColor Green
    Write-Host "📋 Próximos pasos:" -ForegroundColor Cyan
    Write-Host "   1. Asignar usuarios a grupos BYOD y Privileged Roles" -ForegroundColor White
    Write-Host "   2. Probar configuraciones en dispositivos de prueba" -ForegroundColor White
    Write-Host "   3. Monitorear sign-in logs para validar funcionamiento" -ForegroundColor White
    Write-Host "   4. Ajustar políticas según feedback de usuarios" -ForegroundColor White
    
} catch {
    Write-Host "❌ Error en la configuración: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "🔍 Verificar permisos y credenciales" -ForegroundColor Yellow
}
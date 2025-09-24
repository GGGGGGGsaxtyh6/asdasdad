# Guía de Resolución de Problemas BYOD - CAE + Token Protection

## 🚨 PROBLEMAS COMUNES Y SOLUCIONES

### 1. Re-prompts Frecuentes en Outlook Móvil

#### Síntomas:
- Usuario debe autenticarse múltiples veces
- Outlook se cierra inesperadamente
- Mensajes de "Sesión expirada"

#### Causas:
- Token Protection aplicado a apps no soportadas
- CAE revocando tokens en BYOD
- Sign-in frequency demasiado agresivo

#### Solución:
```powershell
# Verificar políticas de Conditional Access
Get-MgIdentityConditionalAccessPolicy | Where-Object { $_.DisplayName -like "*BYOD*" }

# Ajustar sign-in frequency para BYOD
$byodPolicy = Get-MgIdentityConditionalAccessPolicy | Where-Object { $_.DisplayName -eq "BYOD - MAM Only" }
$byodPolicy.SessionControls.SignInFrequency.Value = 8  # 8 horas en lugar de 1
```

### 2. Fallos de Conexión en Teams

#### Síntomas:
- Teams no se conecta
- Error "No se puede conectar al servicio"
- Aplicación se cierra al iniciar

#### Causas:
- App Protection Policy demasiado restrictiva
- Token Protection no soportado en Teams móvil
- Conditional Access bloqueando conexión

#### Solución:
```powershell
# Crear política específica para Teams
$teamsPolicy = @{
    displayName = "BYOD - Teams Mobile"
    targetedApps = @("com.microsoft.teams")
    dataProtection = @{
        encryptOrgData = "Required"
        preventBackup = "Required"
        restrictCutCopyPaste = "Required"
    }
    conditionalLaunch = @{
        maxPinAttempts = 3
        offlineGracePeriod = 240
        jailbrokenDevice = "Block"
    }
}
```

### 3. Problemas de Token Binding

#### Síntomas:
- "Token no válido" en aplicaciones
- Re-autenticación constante
- Errores de "Device binding failed"

#### Causas:
- Token Protection en dispositivos no compatibles
- Cambios de dispositivo no detectados
- Políticas de CAE demasiado estrictas

#### Solución:
```powershell
# Verificar soporte de Token Protection
$tokenProtectionPolicy = Get-MgIdentityTokenProtectionPolicy
$tokenProtectionPolicy.ClientAppTypes = @("publicClient", "confidentialClient")

# Aplicar solo a apps soportadas
$supportedApps = @(
    "com.microsoft.office.outlook",
    "com.microsoft.office.word",
    "com.microsoft.office.excel"
)
```

## 🔧 HERRAMIENTAS DE DIAGNÓSTICO

### 1. Script de Diagnóstico BYOD
```powershell
# Diagnóstico completo de problemas BYOD
function Diagnose-BYODIssues {
    param($UserPrincipalName)
    
    # Obtener sign-in logs del usuario
    $signInLogs = Get-MgAuditLogSignIn -Filter "userPrincipalName eq '$UserPrincipalName'" -Top 50
    
    # Analizar patrones
    $analysis = @{
        TotalSignIns = $signInLogs.Count
        FailedSignIns = ($signInLogs | Where-Object { $_.Status.ErrorCode -ne 0 }).Count
        BYODSignIns = ($signInLogs | Where-Object { $_.DeviceDetail.DeviceOwnership -eq "Personal" }).Count
        MobileAppSignIns = ($signInLogs | Where-Object { $_.ClientAppUsed -like "*Mobile*" }).Count
    }
    
    return $analysis
}
```

### 2. Verificación de App Protection Policies
```powershell
# Verificar políticas MAM aplicadas
function Verify-AppProtectionPolicies {
    param($UserPrincipalName)
    
    $userPolicies = Get-MgDeviceAppManagementManagedAppPolicy -Filter "targetedApps eq 'com.microsoft.office.outlook'"
    
    foreach ($policy in $userPolicies) {
        Write-Host "Política: $($policy.DisplayName)"
        Write-Host "  - Encrypt Org Data: $($policy.DataProtection.EncryptOrgData)"
        Write-Host "  - Prevent Backup: $($policy.DataProtection.PreventBackup)"
        Write-Host "  - Require PIN: $($policy.AccessRequirements.RequirePin)"
    }
}
```

### 3. Monitoreo de CAE
```powershell
# Monitorear eventos de CAE
function Monitor-CAEEvents {
    param($HoursBack = 24)
    
    $startTime = (Get-Date).AddHours(-$HoursBack)
    $caeEvents = Get-MgAuditLogSignIn -Filter "createdDateTime ge $startTime" | Where-Object { $_.AuthenticationRequirement -eq "multiFactorAuthentication" }
    
    $caeAnalysis = @{
        TotalCAEEvents = $caeEvents.Count
        SuccessfulCAE = ($caeEvents | Where-Object { $_.Status.ErrorCode -eq 0 }).Count
        FailedCAE = ($caeEvents | Where-Object { $_.Status.ErrorCode -ne 0 }).Count
    }
    
    return $caeAnalysis
}
```

## 📊 MÉTRICAS DE ÉXITO

### KPIs para BYOD:
- **Tasa de éxito de autenticación**: >95%
- **Tiempo promedio de sign-in**: <30 segundos
- **Re-prompts por usuario por día**: <2
- **Tasa de adopción de MAM**: >80%

### Alertas Automáticas:
```powershell
# Configurar alertas automáticas
function Setup-BYODAlerts {
    $alertConditions = @{
        FailedSignInRate = 10  # >10% de fallos
        RePromptFrequency = 5  # >5 re-prompts por usuario
        CAEFailureRate = 5     # >5% de fallos CAE
    }
    
    # Crear reglas de alerta
    foreach ($condition in $alertConditions.GetEnumerator()) {
        New-MgSecurityAlertRule -DisplayName "BYOD - $($condition.Key)" -Condition $condition.Value
    }
}
```

## 🎯 MEJORES PRÁCTICAS

### 1. Segmentación por Tipo de Cliente
```json
{
  "conditions": {
    "clientApps": ["Mobile apps and desktop clients"],
    "deviceFilters": {
      "rule": "device.deviceOwnership -eq \"Personal\""
    }
  }
}
```

### 2. Políticas de Sesión Optimizadas
```json
{
  "sessionControls": {
    "signInFrequency": {
      "type": "hours",
      "value": 8
    },
    "applicationEnforcedRestrictions": {
      "isEnabled": true
    }
  }
}
```

### 3. App Protection Policies Balanceadas
```json
{
  "dataProtection": {
    "encryptOrgData": "Required",
    "preventBackup": "Required",
    "restrictCutCopyPaste": "Required"
  },
  "conditionalLaunch": {
    "maxPinAttempts": 5,
    "offlineGracePeriod": 720,
    "jailbrokenDevice": "Block"
  }
}
```

## 🔍 VALIDACIÓN Y TESTING

### 1. Testing de Escenarios BYOD
```powershell
# Script de testing automatizado
function Test-BYODScenarios {
    $testUsers = @("test.byod1@company.com", "test.byod2@company.com")
    
    foreach ($user in $testUsers) {
        Write-Host "Testing user: $user"
        
        # Test 1: Sign-in en Outlook móvil
        Test-OutlookMobileSignIn -User $user
        
        # Test 2: Token Protection
        Test-TokenProtection -User $user
        
        # Test 3: CAE
        Test-CAE -User $user
        
        # Test 4: App Protection
        Test-AppProtection -User $user
    }
}
```

### 2. Validación de Configuración
```powershell
# Validar configuración completa
function Validate-BYODConfiguration {
    $validation = @{
        CAEEnabled = (Get-MgIdentityContinuousAccessEvaluationPolicy).IsEnabled
        TokenProtectionEnabled = (Get-MgIdentityTokenProtectionPolicy).IsEnabled
        BYODPolicies = (Get-MgIdentityConditionalAccessPolicy | Where-Object { $_.DisplayName -like "*BYOD*" }).Count
        AppProtectionPolicies = (Get-MgDeviceAppManagementManagedAppPolicy).Count
    }
    
    return $validation
}
```

## 📈 OPTIMIZACIÓN CONTINUA

### 1. Análisis de Tendencias
- Revisar sign-in logs semanalmente
- Identificar patrones de problemas
- Ajustar políticas según feedback

### 2. Feedback de Usuarios
- Encuestas de satisfacción
- Métricas de productividad
- Tiempo de resolución de tickets

### 3. Actualizaciones de Políticas
- Revisar políticas mensualmente
- Aplicar mejores prácticas
- Mantener compliance con regulaciones
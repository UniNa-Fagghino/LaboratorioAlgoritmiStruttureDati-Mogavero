param (
    [string]$Cognome = "",
    [string]$Nome = "",
    [string]$Matricola = ""
)

# Richiedi i dati se non passati come parametri
if (-not $Cognome) {
    $Cognome = Read-Host "Inserisci il tuo Cognome"
}
if (-not $Nome) {
    $Nome = Read-Host "Inserisci il tuo Nome"
}
if (-not $Matricola) {
    $Matricola = Read-Host "Inserisci la tua Matricola"
}

$FolderName = "$Cognome - $Nome - $Matricola"
$DestDir = Join-Path -Path $PSScriptRoot -ChildPath $FolderName
$ZipFile = Join-Path -Path $PSScriptRoot -ChildPath "$FolderName.zip"

Write-Host "Creazione pacchetto di consegna: $FolderName" -ForegroundColor Cyan

# 1. Pulisci se già esistente
if (Test-Path $DestDir) {
    Remove-Item -Recurse -Force $DestDir
}
if (Test-Path $ZipFile) {
    Remove-Item -Force $ZipFile
}

New-Item -ItemType Directory -Path $DestDir | Out-Null

$SourceDir = Join-Path -Path $PSScriptRoot -ChildPath "ProgettoLASD"

# 2. Pulisci i file .class dal sorgente
Write-Host "Pulizia preventiva dei file compilati .class..." -ForegroundColor Yellow
Get-ChildItem -Recurse -Filter *.class -Path $SourceDir | Remove-Item -Force

# 3. Copia rigorosamente solo apsd, zapsdtest, Main.java, makefile
Write-Host "Copia dei soli file richiesti dal bando..." -ForegroundColor Yellow
Copy-Item -Recurse (Join-Path $SourceDir "apsd") (Join-Path $DestDir "apsd")
Copy-Item -Recurse (Join-Path $SourceDir "zapsdtest") (Join-Path $DestDir "zapsdtest")
Copy-Item (Join-Path $SourceDir "Main.java") (Join-Path $DestDir "Main.java")
Copy-Item (Join-Path $SourceDir "makefile") (Join-Path $DestDir "makefile")

# 4. Creazione archivio ZIP
Write-Host "Creazione archivio $ZipFile..." -ForegroundColor Green
Compress-Archive -Path $DestDir -DestinationPath $ZipFile -Force

Write-Host "`nOperazione completata con successo!" -ForegroundColor Green
Write-Host "File pronto per la consegna: $ZipFile"
Write-Host "Contenuto verificato: Cartella '$FolderName' contenente solo apsd, zapsdtest, Main.java, makefile."


# PowerShell script to setup and run the Library Management System

$libDir = "lib"
$dbFile = "src/library.db"
$sqliteDriverUrl = "https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.1.0/sqlite-jdbc-3.45.1.0.jar"
$sqliteDriverFile = "$libDir/sqlite-jdbc.jar"

# Ensure lib directory exists
if (-not (Test-Path -Path $libDir)) {
    New-Item -ItemType Directory -Path $libDir | Out-Null
    Write-Host "Created directory: $libDir"
}

# Download SQLite driver if not exists
if (-not (Test-Path -Path $sqliteDriverFile)) {
    Write-Host "Downloading SQLite JDBC driver..."
    Invoke-WebRequest -Uri $sqliteDriverUrl -OutFile $sqliteDriverFile
    Write-Host "Download complete."
} else {
    Write-Host "SQLite JDBC driver already exists."
}

# Ensure db file exists
if (-not (Test-Path -Path $dbFile)) {
    New-Item -ItemType File -Path $dbFile | Out-Null
    Write-Host "Created empty database file: $dbFile"
}

Write-Host "Compiling Java sources..."
Set-Location -Path "src"

# Use platform specific classpath separator
$separator = [System.IO.Path]::PathSeparator
$classpath = ".${separator}../$sqliteDriverFile"

javac -cp $classpath *.java

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful. Running program..."
    java -cp $classpath Main
} else {
    Write-Host "Compilation failed."
}

Set-Location -Path ".."

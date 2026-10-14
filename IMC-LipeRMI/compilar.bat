@echo off
setlocal
cd /d "%~dp0"
set PKG=johnarrieta\seminario\imc\rmi

echo [1/3] Compilando la libreria comun y generando el JAR...
if exist EjemploLipeRmiLibImc\bin rmdir /s /q EjemploLipeRmiLibImc\bin
javac -encoding UTF-8 -d EjemploLipeRmiLibImc\bin EjemploLipeRmiLibImc\src\%PKG%\lib\*.java || goto error
jar cf EjemploLipeRmiLibImc\dist\EjemploLipeRmiLibImc.jar -C EjemploLipeRmiLibImc\bin . || goto error
copy /y EjemploLipeRmiLibImc\dist\EjemploLipeRmiLibImc.jar EjemploLipeRmiServidorImc\lib\ >nul
copy /y EjemploLipeRmiLibImc\dist\EjemploLipeRmiLibImc.jar EjemploLipeRmiClienteImc\lib\ >nul

echo [2/3] Compilando el servidor...
if exist EjemploLipeRmiServidorImc\bin rmdir /s /q EjemploLipeRmiServidorImc\bin
javac -encoding UTF-8 -cp "EjemploLipeRmiServidorImc\lib\*" -sourcepath EjemploLipeRmiServidorImc\src -d EjemploLipeRmiServidorImc\bin EjemploLipeRmiServidorImc\src\%PKG%\Principal.java || goto error

echo [3/3] Compilando el cliente...
if exist EjemploLipeRmiClienteImc\bin rmdir /s /q EjemploLipeRmiClienteImc\bin
javac -encoding UTF-8 -cp "EjemploLipeRmiClienteImc\lib\*" -sourcepath EjemploLipeRmiClienteImc\src -d EjemploLipeRmiClienteImc\bin EjemploLipeRmiClienteImc\src\%PKG%\Principal.java || goto error

echo.
echo Compilacion exitosa. Ejecute ejecutar-servidor.bat y luego ejecutar-cliente.bat
goto fin
:error
echo.
echo *** ERROR DE COMPILACION ***
:fin
pause

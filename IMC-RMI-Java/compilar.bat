@echo off
setlocal
cd /d "%~dp0"
set PKG=johnarrieta\seminario\imc\rmi

echo [1/3] Compilando la libreria comun y generando el JAR...
if exist EjemploRmiLibImc\bin rmdir /s /q EjemploRmiLibImc\bin
javac -encoding UTF-8 -d EjemploRmiLibImc\bin EjemploRmiLibImc\src\%PKG%\lib\*.java || goto error
jar cf EjemploRmiLibImc\dist\EjemploRmiLibImc.jar -C EjemploRmiLibImc\bin . || goto error
copy /y EjemploRmiLibImc\dist\EjemploRmiLibImc.jar EjemploRmiServidorImc\lib\ >nul
copy /y EjemploRmiLibImc\dist\EjemploRmiLibImc.jar EjemploRmiClienteImc\lib\ >nul

echo [2/3] Compilando el servidor...
if exist EjemploRmiServidorImc\bin rmdir /s /q EjemploRmiServidorImc\bin
javac -encoding UTF-8 -cp "EjemploRmiServidorImc\lib\*" -sourcepath EjemploRmiServidorImc\src -d EjemploRmiServidorImc\bin EjemploRmiServidorImc\src\%PKG%\Principal.java || goto error

echo [3/3] Compilando el cliente...
if exist EjemploRmiClienteImc\bin rmdir /s /q EjemploRmiClienteImc\bin
javac -encoding UTF-8 -cp "EjemploRmiClienteImc\lib\*" -sourcepath EjemploRmiClienteImc\src -d EjemploRmiClienteImc\bin EjemploRmiClienteImc\src\%PKG%\Principal.java || goto error

echo.
echo Compilacion exitosa. Ejecute ejecutar-servidor.bat y luego ejecutar-cliente.bat
goto fin
:error
echo.
echo *** ERROR DE COMPILACION ***
:fin
pause

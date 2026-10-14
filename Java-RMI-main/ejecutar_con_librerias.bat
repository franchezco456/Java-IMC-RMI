@echo off
title Ejecutar Con Librerias (LipeRMI) - Ejercicio 17
color 0B
cls
echo ===================================================================
echo     INICIANDO PROYECTO CON LIBRERIAS (TECNOLOGIA LIPERMI)
echo ===================================================================
echo.
echo [1/2] Levantando el Servidor LipeRMI en una nueva consola...
start "Servidor LipeRMI (Con Librerias)" cmd /k "color 0E && title Servidor LipeRMI && echo ======================================================== && echo   SERVIDOR LIPERMI (CON LIBRERIA) - EJERCICIO 17 && echo ======================================================== && java -cp "lib\lipermi-1.0.1.jar;LipeRmiLibDescuento\dist\LipeRmiLibDescuento.jar;LipeRmiServidorDescuento\dist\LipeRmiServidorDescuento.jar" unicolombo.sistemasdistribuidos.descuento.rmi.Principal"

echo Esperando 2 segundos a que el servidor enlace el socket TCP...
timeout /t 2 /nobreak >nul

echo [2/2] Abriendo la Interfaz Grafica del Cliente LipeRMI...
start "Cliente LipeRMI" java -cp "lib\lipermi-1.0.1.jar;LipeRmiLibDescuento\dist\LipeRmiLibDescuento.jar;LipeRmiClienteDescuento\dist\LipeRmiClienteDescuento.jar" unicolombo.sistemasdistribuidos.descuento.rmi.Principal

echo.
echo ===================================================================
echo  Servidor y Cliente iniciados correctamente!
echo  En la ventana del Cliente:
echo    1. Ve a la pestana CONEXION y haz clic en "Conectar".
echo    2. Ve a la pestana CALCULAR DESCUENTO para realizar calculos.
echo ===================================================================
timeout /t 5

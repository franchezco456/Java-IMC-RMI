@echo off
title Ejecutar Sin Librerias (RMI Estandar) - Ejercicio 17
color 0B
cls
echo ===================================================================
echo   INICIANDO PROYECTO SIN LIBRERIAS (JAVA RMI ESTANDAR NATIVO)
echo ===================================================================
echo.
echo [1/2] Levantando el Servidor RMI Estandar en una nueva consola...
start "Servidor RMI Estandar (Sin Librerias)" cmd /k "color 0A && title Servidor RMI Estandar && echo ======================================================== && echo   SERVIDOR RMI ESTANDAR (SIN LIBRERIA) - EJERCICIO 17 && echo ======================================================== && java -cp "RmiEstandarLibDescuento\dist\RmiEstandarLibDescuento.jar;RmiEstandarServidorDescuento\dist\RmiEstandarServidorDescuento.jar" unicolombo.sistemasdistribuidos.descuento.rmi.Principal"

echo Esperando 2 segundos a que el RMI Registry este listo en el puerto 1099...
timeout /t 2 /nobreak >nul

echo [2/2] Abriendo la Interfaz Grafica del Cliente RMI Estandar...
start "Cliente RMI Estandar" java -cp "RmiEstandarLibDescuento\dist\RmiEstandarLibDescuento.jar;RmiEstandarClienteDescuento\dist\RmiEstandarClienteDescuento.jar" unicolombo.sistemasdistribuidos.descuento.rmi.Principal

echo.
echo ===================================================================
echo  Servidor y Cliente RMI Estandar iniciados correctamente!
echo  En la ventana del Cliente:
echo    1. Ve a la pestana CONEXION y haz clic en "Conectar".
echo    2. Ve a la pestana CALCULAR DESCUENTO para realizar calculos.
echo ===================================================================
timeout /t 5

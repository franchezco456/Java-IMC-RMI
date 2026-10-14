@echo off
title Compilar Todos los Proyectos - Ejercicio 17
color 0A
cls
echo ===================================================================
echo           COMPILANDO TODOS LOS PROYECTOS (AMBAS VERSIONES)
echo ===================================================================

echo.
echo ===================================================================
echo [1/2] COMPILANDO VERSION 1: CON LIBRERIAS (LIPERMI)
echo ===================================================================

echo [1.1] Compilando Libreria LipeRMI (LipeRmiLibDescuento)...
if not exist "LipeRmiLibDescuento\build\classes" mkdir "LipeRmiLibDescuento\build\classes"
if not exist "LipeRmiLibDescuento\dist" mkdir "LipeRmiLibDescuento\dist"
javac -encoding UTF-8 -d "LipeRmiLibDescuento\build\classes" "LipeRmiLibDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\lib\DatosDescuento.java" "LipeRmiLibDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\lib\IRemotaCalculoDescuento.java"
jar -cvf "LipeRmiLibDescuento\dist\LipeRmiLibDescuento.jar" -C "LipeRmiLibDescuento\build\classes" .

echo [1.2] Compilando Servidor LipeRMI (LipeRmiServidorDescuento)...
if not exist "LipeRmiServidorDescuento\build\classes" mkdir "LipeRmiServidorDescuento\build\classes"
if not exist "LipeRmiServidorDescuento\dist" mkdir "LipeRmiServidorDescuento\dist"
javac -encoding UTF-8 -cp "lib\lipermi-1.0.1.jar;LipeRmiLibDescuento\dist\LipeRmiLibDescuento.jar" -d "LipeRmiServidorDescuento\build\classes" "LipeRmiServidorDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\Principal.java" "LipeRmiServidorDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\net\CalculoRmiDescuentoImplem.java" "LipeRmiServidorDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\net\Servidor.java"
jar -cvf "LipeRmiServidorDescuento\dist\LipeRmiServidorDescuento.jar" -C "LipeRmiServidorDescuento\build\classes" .

echo [1.3] Compilando Cliente LipeRMI (LipeRmiClienteDescuento)...
if not exist "LipeRmiClienteDescuento\build\classes" mkdir "LipeRmiClienteDescuento\build\classes"
if not exist "LipeRmiClienteDescuento\dist" mkdir "LipeRmiClienteDescuento\dist"
javac -encoding UTF-8 -cp "lib\lipermi-1.0.1.jar;LipeRmiLibDescuento\dist\LipeRmiLibDescuento.jar" -d "LipeRmiClienteDescuento\build\classes" "LipeRmiClienteDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\Principal.java" "LipeRmiClienteDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\vistas\VentanaPrincipal.java"
jar -cvf "LipeRmiClienteDescuento\dist\LipeRmiClienteDescuento.jar" -C "LipeRmiClienteDescuento\build\classes" .

echo.
echo ===================================================================
echo [2/2] COMPILANDO VERSION 2: SIN LIBRERIAS (RMI ESTANDAR)
echo ===================================================================

echo [2.1] Compilando Libreria Estandar (RmiEstandarLibDescuento)...
if not exist "RmiEstandarLibDescuento\build\classes" mkdir "RmiEstandarLibDescuento\build\classes"
if not exist "RmiEstandarLibDescuento\dist" mkdir "RmiEstandarLibDescuento\dist"
javac -encoding UTF-8 -d "RmiEstandarLibDescuento\build\classes" "RmiEstandarLibDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\lib\DatosDescuento.java" "RmiEstandarLibDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\lib\IRemotaCalculoDescuento.java"
jar -cvf "RmiEstandarLibDescuento\dist\RmiEstandarLibDescuento.jar" -C "RmiEstandarLibDescuento\build\classes" .

echo [2.2] Compilando Servidor Estandar (RmiEstandarServidorDescuento)...
if not exist "RmiEstandarServidorDescuento\build\classes" mkdir "RmiEstandarServidorDescuento\build\classes"
if not exist "RmiEstandarServidorDescuento\dist" mkdir "RmiEstandarServidorDescuento\dist"
javac -encoding UTF-8 -cp "RmiEstandarLibDescuento\dist\RmiEstandarLibDescuento.jar" -d "RmiEstandarServidorDescuento\build\classes" "RmiEstandarServidorDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\Principal.java" "RmiEstandarServidorDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\net\CalculoRmiDescuentoImplem.java" "RmiEstandarServidorDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\net\Servidor.java"
jar -cvf "RmiEstandarServidorDescuento\dist\RmiEstandarServidorDescuento.jar" -C "RmiEstandarServidorDescuento\build\classes" .

echo [2.3] Compilando Cliente Estandar (RmiEstandarClienteDescuento)...
if not exist "RmiEstandarClienteDescuento\build\classes" mkdir "RmiEstandarClienteDescuento\build\classes"
if not exist "RmiEstandarClienteDescuento\dist" mkdir "RmiEstandarClienteDescuento\dist"
javac -encoding UTF-8 -cp "RmiEstandarLibDescuento\dist\RmiEstandarLibDescuento.jar" -d "RmiEstandarClienteDescuento\build\classes" "RmiEstandarClienteDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\Principal.java" "RmiEstandarClienteDescuento\src\unicolombo\sistemasdistribuidos\descuento\rmi\vistas\VentanaPrincipal.java"
jar -cvf "RmiEstandarClienteDescuento\dist\RmiEstandarClienteDescuento.jar" -C "RmiEstandarClienteDescuento\build\classes" .

echo.
echo ===================================================================
echo        TODOS LOS PROYECTOS FUERON COMPILADOS EXITOSAMENTE!
echo ===================================================================
pause

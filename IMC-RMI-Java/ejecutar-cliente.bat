@echo off
cd /d "%~dp0"
title Cliente IMC (IMC-RMI-Java)
java -cp "EjemploRmiClienteImc\bin;EjemploRmiClienteImc\lib\*" johnarrieta.seminario.imc.rmi.Principal
pause

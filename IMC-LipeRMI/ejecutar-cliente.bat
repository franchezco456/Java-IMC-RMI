@echo off
cd /d "%~dp0"
title Cliente IMC (IMC-LipeRMI)
java -cp "EjemploLipeRmiClienteImc\bin;EjemploLipeRmiClienteImc\lib\*" johnarrieta.seminario.imc.rmi.Principal
pause

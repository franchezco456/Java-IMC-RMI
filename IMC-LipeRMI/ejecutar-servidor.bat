@echo off
cd /d "%~dp0"
title Servidor IMC (IMC-LipeRMI)
java -cp "EjemploLipeRmiServidorImc\bin;EjemploLipeRmiServidorImc\lib\*" johnarrieta.seminario.imc.rmi.Principal
pause

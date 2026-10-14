@echo off
cd /d "%~dp0"
title Servidor IMC (IMC-RMI-Java)
java -cp "EjemploRmiServidorImc\bin;EjemploRmiServidorImc\lib\*" johnarrieta.seminario.imc.rmi.Principal
pause

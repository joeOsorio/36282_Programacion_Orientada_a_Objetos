@echo off
REM Compila la practica 6 y corre la prueba automatica (opcion oculta 13).
REM Generado para tomar las capturas del reporte. Se puede borrar despues.
chcp 65001 >nul
cd /d "C:\Users\Okuyt\OneDrive - uabc.edu.mx\9_Semestre\36282_Programacion_Orientada_a_Objetos\1_Practicas\06_Descripcion\src"
javac -encoding UTF-8 -d "..\..\..\output" *.java
if errorlevel 1 (
	echo.
	echo *** Error de compilacion, revisa el mensaje de arriba. ***
	timeout /t 900 >nul
	exit /b 1
)
cd /d "C:\Users\Okuyt\OneDrive - uabc.edu.mx\9_Semestre\36282_Programacion_Orientada_a_Objetos\output"
cls
java Main < "%~dp0cap_test.txt"
echo.
echo ================== Fin de la prueba automatica ==================
timeout /t 1800 >nul

@echo off
rem Usage: compile.bat [--all]
rem 11-11-2022

:usage
echo.
echo    Usage: compile.bat [--all]
echo             -a or --all: Compile all components
echo.
exit /b 1

:parse_arg
rem Set default values
set all_flag=FALSE

rem Parse the arguments
for %%I in (%*) do (
    if "%%I"=="--help" (call :usage)
    if "%%I"=="-h" (call :usage)
    if "%%I"=="--all" (set all_flag=TRUE)
    if "%%I"=="-a" (set all_flag=TRUE)
)

goto :eof

:servidorCentral
cd EstacionTrabajo
call mvn install:install-file -Dfile=JTimeChooser_0.1.0.jar -DgroupId=lu.tudor.santec -DartifactId=jtimechooser -DgeneratePom=true -Dversion=0.1.0 -Dpackaging=jar
call mvn clean package
cd ..
copy /Y EstacionTrabajo\target\estacion-trabajo-0.0.1-SNAPSHOT.jar servidor.jar
goto :eof

:servidorWeb
cd Sitio
call mvn clean package
cd ..
copy /Y Sitio\target\Sitio-0.0.1-SNAPSHOT.war web.war
goto :eof

:servidorMovil
cd SitioMovil
call mvn clean package
cd ..
copy /Y SitioMovil\target\SitioMovil-0.0.1-SNAPSHOT.war movil.war
goto :eof

:finalizar
if not exist "%HOMEPATH%\.turismoUy" mkdir "%HOMEPATH%\.turismoUy"
xcopy /E /Y EstacionTrabajo\img "%HOMEPATH%\.turismoUy\img"
copy /Y EstacionTrabajo\config.properties "%HOMEPATH%\.turismoUy\"
echo pathImagenes=%HOMEPATH%\.turismoUy\img\ >> "%HOMEPATH%\.turismoUy\config.properties"
echo ...
goto :eof

:printMenu
cls
echo ----------------------------------------------
echo -        turismoUy compiling utility         -
echo ----------------------------------------------
echo - Please select an option                    -
echo - 1. Compile All Components                  -
echo - 2. Compile ServidorCentral                 -
echo - 3. Compile ServidorWeb                     -
echo - 4. Compile ServidorMovil                   -
echo - 5. Exit                                    -
echo ----------------------------------------------
set /p input=Input Selection: 
if "%input%"=="1" (
    call :servidorCentral
    call :servidorWeb
    call :servidorMovil
    call :finalizar
) else if "%input%"=="2" (
    call :servidorCentral
) else if "%input%"=="3" (
    call :servidorWeb
) else if "%input%"=="4" (
    call :servidorMovil
) else if "%input%"=="5" (
    exit /b 0
) else (
    cls
    goto :printMenu
)
goto :eof

rem Move Maven settings.xml
if exist settings.xml move /Y settings.xml "%HOMEPATH%\.m2\"

rem Parse the argument
call :parse_arg %*
if "%all_flag%"=="TRUE" (
    call :servidorCentral
    call :servidorWeb
    call :servidorMovil
    call :finalizar
) else (
    call :printMenu
)


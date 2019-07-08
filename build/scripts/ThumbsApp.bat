@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ThumbsApp startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and THUMBS_APP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="--add-exports=javafx.graphics/com.sun.javafx.stage = ALL-UNNAMED" "--add-opens=javafx.controls/javafx.scene.control.skin = ALL-UNNAMED" "--add-exports=javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ThumbsApp-1.0-SNAPSHOT.jar;%APP_HOME%\lib\jfoenix-9.0.8.jar;%APP_HOME%\lib\ikonli-javafx-11.3.3.jar;%APP_HOME%\lib\ikonli-fontawesome-pack-11.3.3.jar;%APP_HOME%\lib\mysql-connector-java-8.0.16.jar;%APP_HOME%\lib\httpclient-4.5.9.jar;%APP_HOME%\lib\json-20180813.jar;%APP_HOME%\lib\javafx-fxml-12-win.jar;%APP_HOME%\lib\javafx-controls-12-win.jar;%APP_HOME%\lib\javafx-controls-12.jar;%APP_HOME%\lib\javafx-graphics-12-win.jar;%APP_HOME%\lib\javafx-graphics-12.jar;%APP_HOME%\lib\javafx-base-12-win.jar;%APP_HOME%\lib\javafx-base-12.jar;%APP_HOME%\lib\ikonli-core-11.3.3.jar;%APP_HOME%\lib\protobuf-java-3.6.1.jar;%APP_HOME%\lib\httpcore-4.4.11.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar

@rem Execute ThumbsApp
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %THUMBS_APP_OPTS%  -classpath "%CLASSPATH%" br.ufop.brTomaz.MainApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable THUMBS_APP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%THUMBS_APP_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega

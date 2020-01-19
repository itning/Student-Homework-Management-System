@echo off
echo author itning
set ROOT_DIR=%~dp0
set JAR_DIR=%ROOT_DIR%pic\
set INSTALL_VERSION=2.0.0.RELEASE
if not exist %JAR_DIR%qq-connect-sdk4J.jar call:file_not_found_func qq-connect-sdk4J.jar
call mvn install:install-file -Dfile=%JAR_DIR%qq-connect-sdk4J.jar -DgroupId=com.qq.connect -DartifactId=qq-connect -Dversion=%INSTALL_VERSION% -Dpackaging=jar
pause
exit

:file_not_found_func
echo file %~1 not found !!!
pause
exit
goto:eof
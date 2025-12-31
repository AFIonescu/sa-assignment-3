@echo off
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%
if "%1"=="" (
    mvn spring-boot:run
) else (
    mvn %*
)

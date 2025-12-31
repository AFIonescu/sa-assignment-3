@echo off
set JAVA_HOME=C:\Program Files\Amazon Corretto\jdk17.0.17_10
set PATH=%JAVA_HOME%\bin;%PATH%
if "%1"=="" (
    mvn spring-boot:run
) else (
    mvn %*
)

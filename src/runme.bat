@echo off

color 07
mode con: cols=80 lines=25
title futility: the game

if exist futility.jar (
    goto CHECK_FOR_JAVA
) else (
    echo hey! where's futility.jar?  ensure that the directory is unzipped. 
    echo.
    echo if that doesn't fix things, try downloading the official build at 
    echo        github.com/llevieux/futility/releases
    echo.
    pause
    exit /B
)


:CHECK_FOR_JAVA
rem check for java.  modified from: https://coderwall.com/p/ww4d9a/windows-search-for-java-installation-in-registry-using-batch-script

setlocal ENABLEEXTENSIONS

if %PROCESSOR_ARCHITECTURE%==AMD64 goto CHECK_JAVA_64
if %PROCESSOR_ARCHITECTURE%==x86 goto CHECK_JAVA_32

:CHECK_JAVA_64
set KEY_NAME="HKEY_LOCAL_MACHINE\Software\Wow6432Node\JavaSoft\Java Runtime Environment"
set VALUE_NAME=CurrentVersion
set VALUE_FAMILYNAME=Java6FamilyVersion

for /F "usebackq skip=2 tokens=1-3" %%A in (`REG QUERY %KEY_NAME% /v %VALUE_NAME% 2^>nul`) DO (
    set ValueName=%%A
    set ValueType=%%B
    set ValueValue=%%C
)

if "%ValueValue%" == "1.6" (
    for /F "usebackq skip=2 tokens=1-3" %%A in (`REG QUERY %KEY_NAME% /v %VALUE_FAMILYNAME% 2^>nul`) DO (
        set ValueFamilyName=%%A
        set ValueFamilyType=%%B
        set ValueFamilyValue=%%C
    )
)
if "%ValueValue%" == "1.6" (
    if "%ValueFamilyValue%" LSS "1.6.0_16" goto JAVA_NOT_INSTALLED
)

SET KEY_NAME="%KEY_NAME:~1,-1%\%ValueValue%"
SET VALUE_NAME=JavaHome

if defined ValueName (
    for /F "usebackq skip=2 tokens=1,2*" %%A in (`REG QUERY %KEY_NAME% /v %VALUE_NAME% 2^>nul`) DO (
        set ValueName2=%%A
        set ValueType2=%%B
        set JRE_PATH2=%%C

        if defined ValueName2 (
            set ValueName = %ValueName2%
            set ValueType = %ValueType2%
            set ValueValue =  %JRE_PATH2%
        )
    )
)

if NOT "%JRE_PATH2%" == "" (
    goto START_FUTILITY
) else (
    goto JAVA_NOT_INSTALLED
)

:CHECK_JAVA_32
set KEY_NAME="HKEY_LOCAL_MACHINE\Software\JavaSoft\Java Runtime Environment"
set VALUE_NAME=CurrentVersion
set VALUE_FAMILYNAME=Java6FamilyVersion

for /F "usebackq skip=2 tokens=1-3" %%A in (`REG QUERY %KEY_NAME% /v %VALUE_NAME% 2^>nul`) DO (
    set ValueName=%%A
    set ValueType=%%B
    set ValueValue=%%C
)

if "%ValueValue%" == "1.6" (
    for /F "usebackq skip=2 tokens=1-3" %%A in (`REG QUERY %KEY_NAME% /v %VALUE_FAMILYNAME% 2^>nul`) DO (
        set ValueFamilyName=%%A
        set ValueFamilyType=%%B
        set ValueFamilyValue=%%C
    )
)
if "%ValueValue%" == "1.6" (
    if "%ValueFamilyValue%" LSS "1.6.0_16" goto JAVA_NOT_INSTALLED
)

SET KEY_NAME="%KEY_NAME:~1,-1%\%ValueValue%"
SET VALUE_NAME=JavaHome

if defined ValueName (
    for /F "usebackq skip=2 tokens=1,2*" %%A in (`REG QUERY %KEY_NAME% /v %VALUE_NAME% 2^>nul`) DO (
        set ValueName2=%%A
        set ValueType2=%%B
        set JRE_PATH2=%%C

        if defined ValueName2 (
            set ValueName = %ValueName2%
            set ValueType = %ValueType2%
            set ValueValue =  %JRE_PATH2%
        )
    )
)

if "%JRE_PATH2%" == "" (
    goto JAVA_NOT_INSTALLED
) else (
    goto START_FUTILITY
)

:JAVA_NOT_INSTALLED
echo hey! you don't have a current version of java!
echo.
echo you'll need it for this.
echo.
echo.
start "" http://www.java.com/en/download/
pause
exit /B

:START_FUTILITY
rem run the jar file (if you open it directly from file explorer, it doesn't run in the commandline)

cls
java -jar futility.jar
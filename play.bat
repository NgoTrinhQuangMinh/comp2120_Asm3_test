@echo off
setlocal
pushd "%~dp0"
call gradlew.bat installDist --console=plain
if errorlevel 1 (
    popd
    exit /b 1
)
call "build\install\comp2120_Asm3_test\bin\comp2120_Asm3_test.bat"
popd

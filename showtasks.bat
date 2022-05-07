call runcrud
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo RUNCRUD has errors - process aborted
goto fail

:runbrowser
start http://localhost:8080/crud/v1/tasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo RUNBROWSER has errors - process aborted
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.

call runcrud.bat
if %ERRORLEVEL% == 0 goto runbrowser
echo.
echo There were problems with running runcrud.bat file
goto fail

:runbrowser
start chrome http://localhost:8080/crud/v1/task/tasks
if %ERRORLEVEL% == 0 goto end
echo.
echo There were problems with starting url
goto fail

:fail
echo.
echo Work failed

:end
echo.
echo Work is finished
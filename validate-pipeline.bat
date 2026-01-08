@echo off
echo ========================================
echo Jenkins Pipeline Validation Script
echo ========================================

echo.
echo 1. Checking Maven installation...
mvn --version
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven not found or not properly configured
    exit /b 1
)

echo.
echo 2. Checking Java installation...
java --version
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java not found or not properly configured
    exit /b 1
)

echo.
echo 3. Validating project structure...
if not exist "pom.xml" (
    echo ERROR: pom.xml not found
    exit /b 1
)
if not exist "Jenkinsfile" (
    echo ERROR: Jenkinsfile not found
    exit /b 1
)
if not exist "src\main\java" (
    echo ERROR: Source directory not found
    exit /b 1
)
if not exist "src\test\java" (
    echo ERROR: Test directory not found
    exit /b 1
)

echo.
echo 4. Checking Maven dependencies...
mvn dependency:resolve -q
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to resolve Maven dependencies
    exit /b 1
)

echo.
echo 5. Compiling project...
mvn clean compile -q
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Compilation failed
    exit /b 1
)

echo.
echo ========================================
echo Pipeline validation completed successfully!
echo ========================================
echo.
echo The following files are ready for Jenkins:
echo - Jenkinsfile (Pipeline definition)
echo - pom.xml (Maven configuration with plugins)
echo - checkstyle.xml (Code quality rules)
echo - Source code and tests
echo.
echo Next steps:
echo 1. Commit and push code to your Git repository
echo 2. Create a new Pipeline job in Jenkins
echo 3. Configure the job to use the Jenkinsfile from SCM
echo 4. Run the pipeline
echo ========================================

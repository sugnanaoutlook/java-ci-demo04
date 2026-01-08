# Jenkins Pipeline Setup Guide

This document provides instructions for setting up the Jenkins pipeline for the Hello World Spring Boot application.

## Prerequisites

### Jenkins Installation
- Jenkins 2.400+ with Pipeline plugin
- Blue Ocean plugin (optional, for better UI)

### Required Tools
- **Maven**: Version 3.9.12 or higher
- **JDK**: Java 17 or higher
- **Git**: For source code management

### Jenkins Global Tool Configuration

Navigate to `Manage Jenkins > Global Tool Configuration` and configure:

#### Maven Configuration
- Name: `Maven-3.9.12`
- Install automatically: ✓
- Version: 3.9.12

#### JDK Configuration  
- Name: `JDK-17`
- Install automatically: ✓
- Version: OpenJDK 17

### Required Jenkins Plugins

Install the following plugins via `Manage Jenkins > Manage Plugins`:

1. **Pipeline Plugin** - Core pipeline functionality
2. **Maven Integration Plugin** - Maven support
3. **JUnit Plugin** - Test result publishing
4. **HTML Publisher Plugin** - HTML report publishing
5. **Email Extension Plugin** - Email notifications
6. **Checkstyle Plugin** - Code quality reports
7. **JaCoCo Plugin** - Code coverage reports

## Pipeline Features

The Jenkins pipeline includes the following stages:

### 1. Checkout
- Retrieves source code from the repository

### 2. Build
- Compiles the Java source code using Maven
- Command: `mvn clean compile`

### 3. Test
- Runs unit tests and integration tests
- Generates test reports and code coverage
- Command: `mvn test`
- Publishes:
  - JUnit test results
  - JaCoCo code coverage reports

### 4. Package
- Creates executable JAR file
- Command: `mvn package -DskipTests`
- Archives build artifacts

### 5. Code Quality Analysis
- Runs Checkstyle analysis
- Command: `mvn checkstyle:checkstyle`
- Publishes Checkstyle reports

### 6. Deploy to Staging
- Deploys application to staging environment
- Only runs on `main` branch
- Starts application on port 8082

## Pipeline Configuration

### Environment Variables
- `MAVEN_OPTS`: Maven options for test execution

### Post-Build Actions
- **Success**: Sends success email notification
- **Failure**: Sends failure email notification  
- **Always**: Cleans workspace

### Conditional Deployment
- Staging deployment only occurs on `main` branch
- Uses Jenkins `when` directive for branch conditions

## Setting Up the Pipeline

1. **Create New Pipeline Job**
   - Go to Jenkins dashboard
   - Click "New Item"
   - Enter job name: `hello-world-spring-boot-pipeline`
   - Select "Pipeline" and click OK

2. **Configure Pipeline**
   - In job configuration, scroll to "Pipeline" section
   - Select "Pipeline script from SCM"
   - Choose "Git" as SCM
   - Enter repository URL
   - Set branch to `*/main`
   - Script Path: `Jenkinsfile`

3. **Configure Build Triggers**
   - Poll SCM: `H/5 * * * *` (every 5 minutes)
   - Or set up webhooks for immediate builds

4. **Configure Email Notifications**
   - Go to `Manage Jenkins > Configure System`
   - Configure SMTP settings under "E-mail Notification"
   - Set default recipients if needed

## Running the Pipeline

### Manual Execution
1. Go to pipeline job page
2. Click "Build Now"
3. Monitor progress in build console

### Automatic Execution
- Pipeline runs automatically on code commits (if webhooks configured)
- Or based on polling schedule

## Viewing Reports

### Test Results
- Available in build page under "Test Result"
- Shows pass/fail status and trends

### Code Coverage
- JaCoCo reports available under "Code Coverage Report"
- Shows line and branch coverage metrics

### Code Quality
- Checkstyle reports available under "Checkstyle Report"
- Shows coding standard violations

## Troubleshooting

### Common Issues

1. **Port Conflicts**
   - If port 8080 is in use, application uses 8081
   - Staging deployment uses port 8082

2. **Maven Tool Not Found**
   - Verify Maven tool configuration in Global Tool Configuration
   - Ensure tool name matches Jenkinsfile reference

3. **Test Failures**
   - Check test reports for failure details
   - Verify application.properties test configuration

4. **Email Notifications Not Working**
   - Check SMTP configuration
   - Verify email addresses in pipeline

### Build Artifacts
- JAR files are archived and available for download
- Located in `target/` directory after successful build

## Security Considerations

- Use Jenkins credentials for sensitive information
- Avoid hardcoding passwords or API keys
- Configure proper user permissions for pipeline jobs
- Use secure connections (HTTPS) for external integrations

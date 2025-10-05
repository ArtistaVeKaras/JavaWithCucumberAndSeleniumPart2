# Environment Setup Guide

This guide provides step-by-step instructions for setting up Java and Maven on both Windows and Mac operating systems.

## Prerequisites

### For Windows:
- Administrator access (for installation)
- At least 4GB of free disk space

### For Mac:
- Administrator access (for installation)
- Homebrew (recommended for Mac users)
- At least 4GB of free disk space

## Java Installation

### Windows:
1. **Download Java 11 (JDK 11)**:
   - Visit [Adoptium Temurin JDK 11](https://adoptium.net/temurin/releases/?version=11) (recommended)
   - Select Windows x64 MSI installer (.msi)
   - Run the installer and follow the installation wizard

2. **Set JAVA_HOME Environment Variable**:
   - Right-click on 'This PC' or 'My Computer' and select 'Properties'
   - Click 'Advanced system settings' > 'Environment Variables'
   - Under 'System variables', click 'New...'
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-11.0.x.x-hotspot` (replace x.x with the specific version number)
   - Click 'OK' to save

3. **Add Java to PATH**:
   - In the same 'Environment Variables' window, find 'Path' under 'System variables' and click 'Edit...'
   - Click 'New' and add: `%JAVA_HOME%\bin`
   - Click 'OK' to save all changes

### Mac:
1. **Using Homebrew (recommended)**:
   ```bash
   brew tap adoptopenjdk/openjdk
   brew install --cask temurin11
   ```

2. **Set JAVA_HOME** (for bash/zsh):
   Add to your `~/.zshrc` or `~/.bash_profile`:
   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home -v 11)
   export PATH=$JAVA_HOME/bin:$PATH
   ```
   Then run:
   ```bash
   source ~/.zshrc  # or source ~/.bash_profile
   ```

## Maven Installation

### Windows:
1. **Download Maven 3.6.0 or later**:
   - Visit [Maven Download Page](https://maven.apache.org/download.cgi)
   - Download the Binary zip archive (e.g., `apache-maven-3.9.5-bin.zip`)
   - Extract the archive to `C:\Program Files\Apache\maven`

2. **Set M2_HOME and Add to PATH**:
   - Open 'Environment Variables' as described in Java setup
   - Under 'System variables', click 'New...'
   - Variable name: `M2_HOME`
   - Variable value: `C:\Program Files\Apache\maven\apache-maven-3.9.5` (adjust version number)
   - Edit 'Path' and add: `%M2_HOME%\bin`
   - Click 'OK' to save all changes

### Mac:
1. **Using Homebrew (recommended)**:
   ```bash
   brew install maven
   ```

2. **Verify Installation**:
   ```bash
   mvn -v
   ```
   This should display the Maven version and Java version being used.

## Verification

Open a new terminal/command prompt and run:

```bash
java -version
javac -version
mvn -v
```

You should see output similar to:
```
java version "11.0.x"
Java(TM) SE Runtime Environment (build 11.0.x+...)
Java HotSpot(TM) 64-Bit Server VM (build 25.xxx, mixed mode)

mvn -v
Apache Maven 3.9.5 (...)
Maven home: ...
Java version: 11.0.x, vendor: Eclipse Adoptium, runtime: ...
```

## Troubleshooting

- **'java' is not recognized**
  - Verify JAVA_HOME is set correctly
  - Ensure %JAVA_HOME%\bin is in your PATH
  - Restart your terminal/IDE after making changes

- **'mvn' is not recognized**
  - Verify M2_HOME is set correctly
  - Ensure %M2_HOME%\bin is in your PATH
  - Restart your terminal/IDE

- **Version conflicts**
  - Ensure you're using the correct versions (Java 11+ and Maven 3.6.0+)
  - Use `update-alternatives` (Linux/Mac) or system environment variables to manage multiple versions

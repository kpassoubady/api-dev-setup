# Installation Guide (Windows)

This guide walks through the development environment setup on Windows 10/11 for the **Fundamentals of API Development** course: JDK, Maven, IntelliJ IDEA, Postman, and Git.

<!-- markdownlint-disable MD033 MD029 MD010-->
<!-- TOC -->

- [Installation Guide (Windows)](#installation-guide-windows)
  - [1. Install Java Development Kit (JDK)](#1-install-java-development-kit-jdk)
  - [2. Install Maven](#2-install-maven)
  - [3. Install Git](#3-install-git)
  - [4. Install IntelliJ IDEA](#4-install-intellij-idea)
  - [5. Install Postman](#5-install-postman)
  - [6. Verification](#6-verification)
  - [7. Troubleshooting](#7-troubleshooting)

<!-- /TOC -->

## 1. Install Java Development Kit (JDK)

Download and install Java 17 or higher (recommended: JDK 21).

1. **Download JDK**:
   - Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html), [OpenJDK](https://jdk.java.net/21/), or [Adoptium](https://adoptium.net/)
   - Download the Windows x64 installer (`.msi`)
2. **Install JDK**:
   - Run the installer and follow the wizard
   - Note the installation path (typically `C:\Program Files\Java\jdk-21`)

### Set JAVA_HOME Environment Variable

1. Press `Windows + R`, type `sysdm.cpl`, press Enter, then open the **Advanced** tab and click **Environment Variables**.
2. Under "System Variables", click **New**:
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-21` (adjust if different)
3. Find **Path** in System Variables, click **Edit**, then **New**, and add: `%JAVA_HOME%\bin`
4. Click **OK** to close all dialogs.

Verify in a new Command Prompt:

```cmd
java -version
javac -version
```

## 2. Install Maven

1. **Download**: Visit [Apache Maven Downloads](https://maven.apache.org/download.cgi) and download the Binary zip archive (e.g. `apache-maven-3.9.6-bin.zip`).
2. **Extract**: Extract to `C:\Program Files\Maven`, so the final path is `C:\Program Files\Maven\apache-maven-3.9.6`.

### Set Maven Environment Variables

1. Open Environment Variables (same as Step 1).
2. Under "System Variables", click **New**:
   - Variable name: `M2_HOME`
   - Variable value: `C:\Program Files\Maven\apache-maven-3.9.6`
3. Find **Path**, click **Edit**, then **New**, and add: `%M2_HOME%\bin`
4. Click **OK** to close all dialogs.

Verify in a new Command Prompt:

```cmd
mvn -version
```

You should see Maven version information along with the Java details from Step 1.

## 3. Install Git

1. Download from [Git for Windows](https://git-scm.com/download/win) and run the installer (default settings are fine).
2. Ensure "Git from the command line and also from 3rd-party software" is selected during install.
3. Configure your identity:

```cmd
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

Verify:

```cmd
git --version
```

## 4. Install IntelliJ IDEA

Download IntelliJ IDEA (Community or Ultimate) from <https://www.jetbrains.com/idea/download/> and run the installer. Open IntelliJ IDEA once after installing so it finishes its first-run setup.

## 5. Install Postman

Download Postman from <https://www.postman.com/downloads/> and run the installer. Open Postman once and sign in (or continue without an account) so it's ready for the labs.

## 6. Verification

Run this end-to-end check in a new Command Prompt:

```cmd
java -version && echo Java OK
mvn -version && echo Maven OK
git --version && echo Git OK
```

Then use the [`quickstart-project`](../quickstart-project/) to confirm IntelliJ, Maven, and Postman work together end to end. See [`../quickstart-project/README.md`](../quickstart-project/README.md) for the steps, or the checklist in [`install.md`](./install.md#quick-verification-checklist).

## 7. Troubleshooting

- **`java`/`mvn` not recognized**: Restart Command Prompt after setting environment variables, and double-check `Path` includes `%JAVA_HOME%\bin` and `%M2_HOME%\bin`.
- **Wrong Java version picked up**: Run `echo %JAVA_HOME%` and confirm it points at the JDK you intend to use.
- **IntelliJ doesn't detect the JDK**: In IntelliJ, go to **File > Project Structure > SDKs** and add the JDK path from `echo %JAVA_HOME%`.
- **Maven can't download dependencies**: Check your internet connection, or ask your DevOps team for an organization-specific `settings.xml` (place it in `C:\Users\%USERNAME%\.m2\settings.xml`) if you're behind a corporate proxy.
- **Path issues**: Avoid spaces in folder names where possible, and use backslashes (`\`) for Windows paths.

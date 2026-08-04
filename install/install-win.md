# Installation Guide (Windows)

Development environment setup on Windows 10/11 for the **Fundamentals of API Development** course: JDK, Maven, IntelliJ IDEA (or VS Code), Postman, and Git.

<!-- markdownlint-disable MD033 MD029 MD010-->
<!-- TOC -->

- [Installation Guide (Windows)](#installation-guide-windows)
  - [1. Install Java Development Kit (JDK)](#1-install-java-development-kit-jdk)
  - [2. Install Maven](#2-install-maven)
  - [3. Install Git](#3-install-git)
  - [4. Install IntelliJ IDEA](#4-install-intellij-idea)
  - [5. Install VS Code (alternative IDE)](#5-install-vs-code-alternative-ide)
  - [6. Install Postman](#6-install-postman)
  - [7. Verification](#7-verification)
  - [8. Troubleshooting](#8-troubleshooting)

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

## 5. Install VS Code (alternative IDE)

If you prefer VS Code over IntelliJ IDEA, install it and the required Java extensions. Skip this section if you installed IntelliJ in step 4; you only need one IDE.

Download VS Code from <https://code.visualstudio.com/> and run the installer (default settings are fine).

Open VS Code once after installing so it finishes its first-run setup.

### Install Java Extensions

Open VS Code, press `Ctrl+Shift+X` to open the Extensions view, and install these three extensions:

| Extension | Marketplace ID | Purpose |
|-----------|---------------|---------|
| **Extension Pack for Java** | `vscjava.vscode-java-pack` | Bundles Language Support for Java, Debugger, Test Runner, Maven, and Project Manager: everything needed to work with the course's Spring Boot projects |
| **Spring Boot Extension Pack** | `vmware.vscode-boot-dev-pack` | Spring Tools for VS Code: content assist for `application.properties`/`.yml`, Spring-specific code navigation (`@/` for request mappings, `@+` for beans), and a Spring Boot Dashboard to start, stop, and debug projects |
| **Code Runner** | `formulahendry.vscode-code-runner` | Run Java and many other languages directly from the editor with a single click or keyboard shortcut |

Alternatively, install all three from a terminal (Command Prompt or PowerShell):

```cmd
code --install-extension vscjava.vscode-java-pack
code --install-extension vmware.vscode-boot-dev-pack
code --install-extension formulahendry.vscode-code-runner
```

To open the quickstart project in VS Code:

```cmd
code ..\quickstart-project
```

VS Code will detect the `pom.xml` and prompt you to import the Maven project. Accept it, and the Java extension pack will configure the classpath automatically.

## 6. Install Postman

Download Postman from <https://www.postman.com/downloads/> and run the installer. Open Postman once and sign in (or continue without an account) so it's ready for the labs.

## 7. Verification

Run this end-to-end check in a new Command Prompt:

```cmd
java -version && echo Java OK
mvn -version && echo Maven OK
git --version && echo Git OK
```

Then use the [quickstart-project](https://github.com/kpassoubady/api-dev-setup/tree/main/quickstart-project) to confirm IntelliJ, Maven, and Postman work together end to end. See the [quickstart-project README](https://github.com/kpassoubady/api-dev-setup/blob/main/quickstart-project/README.md) for the steps, or the checklist in the [Installation Instructions](https://github.com/kpassoubady/api-dev-setup/blob/main/install/install.md#quick-verification-checklist).

## 8. Troubleshooting

- **`java`/`mvn` not recognized**: Restart Command Prompt after setting environment variables, and double-check `Path` includes `%JAVA_HOME%\bin` and `%M2_HOME%\bin`.
- **Wrong Java version picked up**: Run `echo %JAVA_HOME%` and confirm it points at the JDK you intend to use.
- **IntelliJ doesn't detect the JDK**: In IntelliJ, go to **File > Project Structure > SDKs** and add the JDK path from `echo %JAVA_HOME%`.
- **Maven can't download dependencies**: Check your internet connection, or ask your DevOps team for an organization-specific `settings.xml` (place it in `C:\Users\%USERNAME%\.m2\settings.xml`) if you're behind a corporate proxy.
- **Path issues**: Avoid spaces in folder names where possible, and use backslashes (`\`) for Windows paths.

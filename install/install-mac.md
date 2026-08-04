# Installation Guide (macOS)

Development environment setup on macOS for the **Fundamentals of API Development** course: JDK, Maven, IntelliJ IDEA (or VS Code), Postman, and Git.

<!-- markdownlint-disable MD033 MD029 MD010-->
<!-- TOC -->

- [Installation Guide (macOS)](#installation-guide-macos)
  - [1. Install Homebrew (if not already installed)](#1-install-homebrew-if-not-already-installed)
  - [2. Install Java Development Kit (JDK)](#2-install-java-development-kit-jdk)
  - [3. Install Maven](#3-install-maven)
  - [4. Install Git](#4-install-git)
  - [5. Install IntelliJ IDEA](#5-install-intellij-idea)
  - [6. Install VS Code (alternative IDE)](#6-install-vs-code-alternative-ide)
  - [7. Install Postman](#7-install-postman)
  - [8. Verification](#8-verification)
  - [9. Troubleshooting](#9-troubleshooting)

<!-- /TOC -->

## 1. Install Homebrew (if not already installed)

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Verify:

```bash
brew --version
```

## 2. Install Java Development Kit (JDK)

Install OpenJDK 17 or higher (recommended: JDK 21):

```bash
brew install openjdk@21
```

Create a symbolic link for system-wide access:

```bash
sudo ln -sfn /usr/local/opt/openjdk@21/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-21.jdk
```

Add OpenJDK to your PATH and set `JAVA_HOME` in your shell profile:

```bash
echo 'export PATH="/usr/local/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
echo 'export JAVA_HOME=`/usr/libexec/java_home -v 21`' >> ~/.zshrc
source ~/.zshrc
```

> On Apple Silicon (M1/M2/M3), Homebrew usually installs under `/opt/homebrew` instead of `/usr/local`. If `brew --prefix` prints `/opt/homebrew`, adjust the paths above:
>
> ```bash
> echo 'export PATH="$(brew --prefix)/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
> sudo ln -sfn "$(brew --prefix)/opt/openjdk@21/libexec/openjdk.jdk" /Library/Java/JavaVirtualMachines/openjdk-21.jdk
> source ~/.zshrc
> ```

Validate:

```bash
java -version
javac -version
```

## 3. Install Maven

```bash
brew install maven
```

Verify:

```bash
mvn -version
```

You should see Maven version information along with the Java details from Step 2.

## 4. Install Git

```bash
brew install git
```

Configure Git with your information:

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

Verify:

```bash
git --version
```

## 5. Install IntelliJ IDEA

Download IntelliJ IDEA (Community or Ultimate) from <https://www.jetbrains.com/idea/download/>, or install via Homebrew:

```bash
brew install --cask intellij-idea-ce
```

Open IntelliJ IDEA once after installing so it finishes its first-run setup.

## 6. Install VS Code (alternative IDE)

If you prefer VS Code over IntelliJ IDEA, install it and the required Java extensions. Skip this section if you installed IntelliJ in step 5; you only need one IDE.

Download VS Code from <https://code.visualstudio.com/>, or install via Homebrew:

```bash
brew install --cask visual-studio-code
```

Open VS Code once after installing so it finishes its first-run setup.

### Install Java Extensions

Open VS Code, press `Cmd+Shift+X` to open the Extensions view, and install these three extensions:

| Extension | Marketplace ID | Purpose |
|-----------|---------------|---------|
| **Extension Pack for Java** | `vscjava.vscode-java-pack` | Bundles Language Support for Java, Debugger, Test Runner, Maven, and Project Manager: everything needed to work with the course's Spring Boot projects |
| **Spring Boot Extension Pack** | `vmware.vscode-boot-dev-pack` | Spring Tools for VS Code: content assist for `application.properties`/`.yml`, Spring-specific code navigation (`@/` for request mappings, `@+` for beans), and a Spring Boot Dashboard to start, stop, and debug projects |
| **Code Runner** | `formulahendry.vscode-code-runner` | Run Java and many other languages directly from the editor with a single click or keyboard shortcut |

Alternatively, install all three from the terminal:

```bash
code --install-extension vscjava.vscode-java-pack
code --install-extension vmware.vscode-boot-dev-pack
code --install-extension formulahendry.vscode-code-runner
```

To open the quickstart project in VS Code:

```bash
code ../quickstart-project
```

VS Code will detect the `pom.xml` and prompt you to import the Maven project. Accept it, and the Java extension pack will configure the classpath automatically.

## 7. Install Postman

Download Postman from <https://www.postman.com/downloads/>, or install via Homebrew:

```bash
brew install --cask postman
```

Open Postman once and sign in (or continue without an account) so it's ready for the labs.

## 8. Verification

Run this end-to-end check before class:

```bash
java -version && echo "✓ Java OK"
mvn -version && echo "✓ Maven OK"
git --version && echo "✓ Git OK"
code --version && echo "✓ VS Code OK"
```

Then use the [quickstart-project](https://github.com/kpassoubady/api-dev-setup/tree/main/quickstart-project) to confirm IntelliJ, Maven, and Postman work together end to end. See the [quickstart-project README](https://github.com/kpassoubady/api-dev-setup/blob/main/quickstart-project/README.md) for the steps, or the checklist in the [Installation Instructions](https://github.com/kpassoubady/api-dev-setup/blob/main/install/install.md#quick-verification-checklist).

## 9. Troubleshooting

- **`java`/`mvn` not found after install**: Restart your terminal, or re-run `source ~/.zshrc`.
- **Wrong Java version picked up**: Run `/usr/libexec/java_home -V` to list installed JDKs, and confirm `JAVA_HOME` points at the one you want.
- **IntelliJ doesn't detect the JDK**: In IntelliJ, go to **File > Project Structure > SDKs** and add the JDK path from `echo $JAVA_HOME`.
- **Maven can't download dependencies**: Check your internet connection, or ask your DevOps team for an organization-specific `~/.m2/settings.xml` if you're behind a corporate proxy.

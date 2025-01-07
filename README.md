# Playwright Automated Testing Framework

[![run](https://github.com/cmccarthyIrl/spring-cucumber-testng-parallel-test-harness/actions/workflows/run.yml/badge.svg)](https://github.com/cmccarthyIrl/spring-cucumber-testng-parallel-test-harness/actions/workflows/run.yml)

# Index
<table> 
<tr>
  <th>Start</th>
  <td>
    | <a href="#maven">Maven</a> 
    | <a href="#quickstart">Quickstart</a> | 
  </td>
</tr>
<tr>
  <th>Run</th>
  <td>
     | <a href="#command-line">Command Line</a>
    | <a href="#ide-support">IDE Support</a>    
    | <a href="#java-jdk">Java JDK</a>    
    | <a href="#troubleshooting">Troubleshooting</a>    |
  </td>
</tr>
<tr>
  <th>Report</th> 
  <td>
     | <a href="#configuration">Configuration</a>
    | <a href="#logging">Logging</a> |
  </td>
</tr>
<tr>
  <th>Advanced</th>
  <td>
    | <a href="#contributing">Contributing</a> |
    </td>
</tr>
</table>

# Playwright Java Test Harness

This project is a test harness for using Playwright with Java for both UI and API testing. It is set up as a multi-module Maven project with two modules: `ui` for UI-based tests and `api` for API-based tests.

# Maven

The Framework uses [Playwright](https://spring.io/guides/gs/testing-web/) and [TestNG](https://testng.org/) client implementations.

```xml
      <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright</artifactId>
            <version>1.49.0</version>
        </dependency>

        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.10.2</version>
        </dependency>
```



# Quickstart

- [Intellij IDE](https://www.jetbrains.com/idea/) - `Recommended`
- [Java JDK 17](https://jdk.java.net/java-se-ri/11)
- [Apache Maven](https://maven.apache.org/docs/3.6.3/release-notes.html)

# Command Line

Normally you will use your IDE to run a test via the `*Test.java` class. With the `Test` class,
we can run tests from the command-line as well.

Note that the `mvn test` command only runs test classes that follow the `*Test.java` naming convention.

You can run a single test or a suite or tests like so :

```
mvn test -Dtest=WikipediaParallelRunnerTest
```

Note that the `mvn clean install` command runs all test Classes that follow the `*Test.java` naming convention

```
mvn clean install
```

# IDE Support

To minimize the discrepancies between IDE versions and Locales the `<sourceEncoding>` is set to `UTF-8`

```xml

<properties>
    ...
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    ...
</properties>
```

# Java JDK

The Java version to use is defined in the `maven-compiler-plugin`

```xml

<build>
    ...
    <pluginManagement>
        <plugins>
            ...
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            ...
        </plugins>
    </pluginManagement>
    ...
</build>
```

# Logging

The Framework uses [Log4j2](https://logging.apache.org/log4j/2.x/) You can instantiate the logging service in any Class
like so

```java
private final Logger logger=LoggerFactory.getLogger(WikipediaPageSteps.class);
```

you can then use the logger like so :

```java
logger.info("This is a info message");
logger.warn("This is a warning message");
logger.debug("This is a info message");
logger.error("This is a error message");
```

# License
This project is open source and available under the [MIT License](https://github.com/cmccarthyIrl/playwright-java-test-harness/blob/main/LICENSE).

# Troubleshooting

- Execute the following commands to resolve any dependency issues
    1. `cd ~/install directory path/playwright-java-test-harness`
    2. `mvn clean install -DskipTests`

# Contributing

Spotted a mistake? Questions? Suggestions?

[Open an Issue](https://github.com/cmccarthyIrl/playwright-java-test-harness/issues)


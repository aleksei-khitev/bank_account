## BANK ACCOUNT

This is a project as a test task.

## Project Structure

This is multimodule maven project, based on *Java 8*.

It contains projects:
*. *db* - for database connections, entity description, repository, based on spring jpa,
*. *jms* - for jms sender and listener services,
*. *web* - for Spring MVC configuration.

Project has in memory hsql data base with test and production data sets.
JMS settings is writen in `resources/jms.properties`
Spring DI and JSR-330 annotations are used for dependency injection.
REST controller is covered by unit tests.
Selenide (project based on Selenium) is confired for UI tests, but there are no test cases by now.
There starting active MQ for jms testing.
For accounts representing there are jquery datatable configured.

## Installation

1. check that java 8 is installed on the server (execute 'java -version'),
1. check that `JAVA_HOME` environment variable configured and `$JAVA_HOME\bin` is added to the `PATH`,
1. check that [maven](https://maven.apache.org/download.cgi) installed
1. invoke `mvn clean install`,
1. after this, there will be *web.war* in {path to project}/web/target generated for deployment,
1. install in application (or web with java support) server. For example [this](https://tomcat.apache.org/tomcat-6.0-doc/deployer-howto.html) is deployment manual for apache tomcat server.


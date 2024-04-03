# Introduction to Software Development

This repo contains the code for the labs in Jacks classes each week. This repo is for VsCode users, if you wish to use Netbeans, please visit this repo: https://github.com/jiak1/ISDAutLab-Netbeans

Make sure you first install:

- Java JDK (https://www.oracle.com/au/java/technologies/downloads/)
- Maven (https://maven.apache.org/download.cgi) Select the **binary version** not Source

To Run The Project:

Open 2 terminals, side by side in VsCode:

Every time you make a change, run:

`mvn package`

In the other terminal, just run the following & leave it open for as long as you are developing:
`mvn jetty:run`

Then, open a web browser & visit: `http://localhost:8080`

To run unit tests: `mvn test`

## Help Installing Maven

### Mac Users

Macos Users will need to edit their bash_profile file in ~/.bash_profile

Please add the following (Update the path to be wherever you downloaded the maven folder):

```
export M2_HOME="/Users/jack/Downloads/apache-maven-3.6.3"
PATH="${M2_HOME}/bin:${PATH}"
export PATH
```

You can confirm maven was succesfully installed by running `mvn` in your terminal.

### Windows Users

After installing Maven, search for environment variables in windows. This will open a dialogue like so:

![Alt text](images/env.PNG?raw=true 'Environment Variables')

Click the new button under system variables & add one called MAVEN_HOME with a link to the downloaded zip file:

![Alt text](images/systemvar.PNG?raw=true 'Environment Variables')

Find the PATH Variable under user variables & click edit. On the next screen, add a new entry with the value `%MAVEN_HOME%\bin`:

![Alt text](images/uservar.PNG?raw=true 'User Variables')

Close all the menus, reload a terminal window & you can confirm maven was succesfully installed by running `mvn` in your terminal.

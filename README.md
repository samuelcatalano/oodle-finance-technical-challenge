# Oodle Finance
### Oodle Finance Code Challenge

## Tech Stack
| Technology | Version |
|--|--|
| **Java** | 17.0.1 2021-10-19 |
| **Spring Boot** | 3.0.2.RELEASE |
| **Consult Discovery** | 3.0.2.RELEASE |
| **Open Feign** | 3.0.2.RELEASE |
| **Project Lombok** | 1.18.24 |
| **JUnit 4/5** | 4.1.5 - 5.6.2 |
| **Model Mapper** | 3.1.0 |
| **Mockito** | 4.8.1 |
| **H2 Memory** | 2.1.212 |
| **Springdoc OpenAPI Swagger** | 2.0.2 |

## Consul Service Discovery
### Installing Consul

Consul is used for service discovery and configuration. To install Consul, follow the steps below:

1. Download Consul from https://www.consul.io/downloads.html
2. Unzip the file and navigate to the folder in the command prompt.
3. Run the following command in the command prompt:

For Windows:
`consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=127.0.0.1`

For Unix:
`./consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=127.0.0.1`

For Mac:
`brew install consul`

To list the available members in the consul, run the following command: `consul members`.   
Alternatively, you can access the URL http://localhost:8500/ui/dc1/services to view the members.


## Database
As soon as you start the **internal** application, the _.ddl_ will create the table automatically.

#### Access H2 Memory Database:

Once with the application running you can access: http://localhost:8080/h2-console

> jdbc: `jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;DATABASE_TO_UPPER=false;`  
> user: `admin`  
> pass: `admin`

## Running the Application
You can run the application using your favorite IDE (IntelliJ, Eclipse, NetBeans) by importing the project as a Maven/Gradle project, building the project with Java 17, and running/debugging the project from the Main Application Class.:

For Internal: `InternalApplication.class`  
For External: `ExternalApplication.class`

### Terminal

#### For internal (in the folder /oodle-coding-challenge/samuel.catalano.internal):
`./gradlew bootRun`

#### For external (in the folder /oodle-coding-challenge/samuel.catalano.external):
`./gradlew bootRun`

## Running the Tests

#### Internal - /oodle-coding-challenge/samuel.catalano.internal
`./gradlew test`

#### External - /oodle-coding-challenge/samuel.catalano.external
`./gradlew test`

## Acess Swagger Open Rest API:
- External: http://localhost:8081/swagger-ui.html

## APIs:

> **POST**  
http://localhost:8081/api/messages

> json:
```javascript
{"message": "Creating a new message!"}
```

> **PUT**  
http://localhost:8081/api/messages/1

> json:
```javascript
{"message": "Updating an existing message!"}
```

> **GET**  
Retrieve an existing message:
http://localhost:8081/api/messages/1

> **GET**  
Retrieve all messages:
http://localhost:8081/api/messages

> **DELETE**  
Delete an existing message:
http://localhost:8081/api/messages/1

## Possible Improvements

1) Upgrading the dependencies to take advantage of native-image support with GraalVM, which could result in a faster and lighter application.
2) Integrating spring-security for enhanced security measures.
3) Adopting a database migration tool like Flyway or Liquibase to streamline database management.
4) Establishing a CI/CD pipeline to automate deployment and enable seamless integrations.
5) Not deploying the app on a server due to limitations with Heroku and the use of free credits on AWS account. However, the ability to explain the deployment process in a technical interview would be a valuable asset.

# Welcome to ONE Record Java Sandbox!
This repository contains the code and instructions that will help you deploy your first ONE Record Server. Please follow the instructions in order to get the application running.

# Configuration 
Before deploying the server, you will need to update the `application.properties` file with:
1. the credentials for accessing your MongoDB database. You can find the instructions on how to setup a MongoDB database below.  
`    spring.data.mongodb.uri:mongodb+srv://<username>:<password>@<databaseURI>/<databaseName>?retryWrites=true&w=majority
`  
2. path to a directory in which temporary certificates will be stored.
    `    ocsp.cachedDir=C:\\`

# Local testing

## Build & run the sandbox code
For testing the application locally, follow the next steps:
1. Install `Java = 8` and `Maven`. 
2. Install the Maven dependencies by running `mvn clean install`. 
As the latest models are already generated and commited in this repository according to the latest ontologies, kindly skip steps 3, 4, 5 and 6.
3. (Optional) Download the latest ONE Record Turtle ontologies from Github + the latest Web Access Control from W3C by running `mvn exec:java`.
4. (Optional) Generate ONE Record cargo related data model by running `mvn package -Dbuild=cargo`
5. (Optional) Generate ONE Record API related models by running `mvn package -Dbuild=api`
6. (Optional) Generate W3C Web Access Control ACL (Access Control List) related models by running `mvn package -Dbuild=acl`
7. Run the server from OneRecordApplication or from the command line via `mvn spring-boot:run`. Your application should be available at [http://localhost:8080](http://localhost:8080).

## Swagger API Documentation
Swagger API Documentation can be accessed and tested via [https://yourserverurl/swagger-ui.html](https://yourserverurl/swagger-ui.html). If you run the server locally, Swagger documentation can be accessed via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Postman collection
A Postman collection containing ONE Record API endpoints ready for testing can be found [here](https://github.com/IATA-Cargo/one-record-server-java/tree/master/src/test/resources/postman).

## JSON-LD models
JSON-LD generated files for the ONE Record Cargo related models and the API specific models can be found [here](https://github.com/IATA-Cargo/ONE-Record/tree/master/working_draft/API/json-ld).
Some examples with test values can be also found [here](https://github.com/IATA-Cargo/one-record-server-java/tree/master/src/test/resources/examples).

# Deployment
## MongoDB database 
ONE Record Server uses a MongoDB database. In order to setup quickly your own MongoDB environment, we suggest you to use a cloud database service such as [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).

1. To get started with MongoDB Atlas, [follow these steps](https://docs.atlas.mongodb.com/getting-started/). 
2. Add your database URL to `application.properties` file: `'spring.data.mongodb.uri': 'mongodb+srv://<username>:<password>@<databaseURI>/<databaseName>?retryWrites=true&w=majority'`

# Security in ONE Record
By default, the security layer supporting mutual TLS is disabled in this sandbox, however the needed code can be found under `com.wisekey.ocsp` package and can be tested via
`SslClientAuthenticationTestResource` controller. If one wish to test mutual TLS, test certificates can be generated via [IATA Certificate Portal](https://iatacms.wisekeydemo.com/web).
Instructions for creating a subscriber account, as well as ONE Record security documentation can be found [here](https://github.com/IATA-Cargo/ONE-Record/tree/master/working_draft/security).

# IATA ONE Record
You can find further information about ONE Record specifications on the [main Github repository](https://github.com/IATA-Cargo/ONE-Record), 
on the [ONE Record Developer Poral](http://www.onerecordcargo.org/index.html) and on the [IATA dedicated webpage](https://www.iata.org/en/programs/cargo/e/one-record/).


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
For testing the application locally, follow the next steps:
1. Install `Java = 8` and `Maven`. 
2. Install the Maven dependencies by running `mvn clean install`
3. (Optional) Download the latest ONE Record Turtle ontologies from Github + the latest Web Access Control from W3C by running `mvn exec:java`.
4. (Optional) Generate ONE Record cargo related data model by running `mvn package -Dbuild=cargo`
5. (Optional) Generate ONE Record API related models by running `mvn package -Dbuild=api`
6. (Optional) Generate W3C Web Access Control ACL (Access Control List) related models by running `mvn package -Dbuild=acl`
7. Run the server from OneRecordApplication or from the command line via `mvn spring-boot:run`. Your application should be available at [http://localhost:8080](http://localhost:8080).

## Swagger API Documentation
Swagger API Documentation can be accessed and tested via [https://yourserverurl/swagger-ui.html](https://yourserverurl/swagger-ui.html). If you run the server locally, Swagger documentation can be accessed via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

# Deployment
## MongoDB database 
ONE Record Server uses a MongoDB database. In order to setup quickly your own MongoDB environment, we suggest you to use a cloud database service such as [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).

1. To get started with MongoDB Atlas, [follow these steps](https://docs.atlas.mongodb.com/getting-started/). 
2. Add your database URL to `application.properties` file: `'spring.data.mongodb.uri': 'mongodb+srv://<username>:<password>@<databaseURI>/<databaseName>?retryWrites=true&w=majority'`

## IATA ONE Record
You can find further information about ONE Record specifications on the [main Github repository](https://github.com/IATA-Cargo/ONE-Record) and on the [IATA dedicated webpage](https://www.iata.org/en/programs/cargo/e/one-record/).

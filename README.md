# Welcome to ONE Record Java Sandbox!
This repository contains the code and instructions that will help you deploy your first ONE Record Server. Please follow the instructions in order to get the application running.

# Configuration
Before deploying the server, you will need to update the `application.properties` file with:
1. the credentials for accessing your MongoDB database. You can find the instructions on how to setup a MongoDB database below.  
`    spring.data.mongodb.uri:mongodb+srv://<username>:<password>@<databaseURI>/<databaseName>?retryWrites=true&w=majority
`  
2. the name of your database
`spring.data.mongodb.database=onerecordcargo
`
3. path to a directory in which temporary certificates will be stored.

    `    ocsp.cachedDir=C:\\`

- Configuration cache directory which is stored crl files.

    'ocsp.cache': ''

2. Use your client/ca certificate files
Add ca certificate into src/main/resources/truststore.jks file.
Convert client certificate to jks.
Convert ca certificate to jks.
Copy this jks files to one-record-client-java project which clone from [one-record-client-java](https://github.com/IATA-Cargo/one-record-client-java)

# Local testing
For testing the application locally, follow the next steps:
1. Install `Java >= 8` and `Maven`.
2. Generate the Java beans from the ONE Record Turtle ontology by running `mvn exec:java`.
3. Install the Maven dependencies by running `mvn clean install`
4. Generate cargo related data model by running `mvn package -Dbuild=cargo`
5. Generate API related models by running `mvn package -Dbuild=api`
6. Run the server from OneRecordApplication or from the command line via `mvn spring-boot:run`. Your application should be available at [http://localhost:8080](http://localhost:8080).

## Swagger API Documentation
Swagger API Documentation can be accessed and tested via [https://yourserverurl/swagger-ui.html](https://yourserverurl/swagger-ui.html). If you run the server locally, Swagger documentation can be accessed via [https://localhost:8443/swagger-ui.html](https://localhost:8443/swagger-ui.html).

# Deployment
## MongoDB database
ONE Record Server uses a MongoDB database. In order to setup quickly your own MongoDB environment, we suggest you to use a cloud database service such as [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).

1. To get started with MongoDB Atlas, [follow these steps](https://docs.atlas.mongodb.com/getting-started/).
2. Add your database URL to `application.properties` file: `'spring.data.mongodb.uri': 'mongodb+srv://<username>:<password>@<databaseURI>/<databaseName>?retryWrites=true&w=majority'`

## IATA ONE Record
You can find further information about ONE Record specifications [here](https://github.com/IATA-Cargo/ONE-Record).

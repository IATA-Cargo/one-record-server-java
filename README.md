# Welcome to ONE Record Java Sandbox!
This repository contains the code and instructions that will help you deploy your first ONE Record Server. Please follow the instructions in order to get the application running.

# Configuration 
Before deploying the server, you will need to update the `application.properties` file with the credentials for accessing your MongoDB database. You can find the instructions on how to setup a MongoDB database below.
   
    'spring.data.mongodb.host': ''
    'spring.data.mongodb.port': '',
    'spring.data.mongodb.authentication-database': '',
    'spring.data.mongodb.username':'',
    'spring.data.mongodb.password': '',
    'spring.data.mongodb.database': ''

# Local testing
For testing the application locally, follow the next steps:
1. Install `Java >= 8` and `Maven`. 
2. Generate the Java beans from the ONE Record Turtle ontology by running `mvn exec:java`.
3. Install the Maven dependencies by running `mvn clean install`
4. Generate cargo related data model by running `mvn package -Dbuild=cargo`
5. Generate API related models by running `mvn package -Dbuild=api`
6. Run the server from OneRecordApplication or from the command line via `mvn spring-boot:run`. Your application should be available at [http://localhost:8080](http://localhost:8080).

## Swagger API Documentation
Swagger API Documentation can be accessed and tested via [http://yourserverurl/swagger-ui.html](http://yourserverurl/swagger-ui.html). If you run the server locally, Swagger documentation can be accessed via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

# Deployment
## MongoDB database
ONE Record Server uses a MongoDB database. In order to setup quickly your own MongoDB environment, we suggest you to use a cloud database service such as [mLab](https://mlab.com/home).

1. To get started with mLab, you must first [create your free mLab account](https://mlab.com/signup). When that’s complete, you can add as many database subscriptions as you want. 
2. After you’ve created your account,  [add a new database subscription](https://mlab.com/create/wizard). Get started at no cost by creating a free Sandbox database.
3. Create a database user and password and retrieve your connection info after logging into your account and navigating to the database’s home page:
![enter image description here](https://docs.mlab.com/assets/screenshot-connectinfo.png)
4. Add your database URL to `config.js` file: `'mongoUrl': 'mongodb://user:password@url:port/yourdb'`

## IATA ONE Record
You can find further information about ONE Record specifications [here](https://github.com/IATA-Cargo/ONE-Record).

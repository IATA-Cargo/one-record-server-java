# Welcome to ONE Record Java Sandbox
This repository contains the code and instructions that will help you deploy your first ONE Record Server. Please follow the instructions in order to get the application running.

# Configuration 
Before deploying the server, you will need to update the `application.properties` file with:
1. the credentials for accessing your MongoDB database. You can find the instructions on how to setup a MongoDB database below.  
`    spring.data.mongodb.uri:mongodb+srv://<username>:<password>@<databaseURI>/<databaseName>?retryWrites=true&w=majority
`  

There are two `application.properties` files to edit:
* One under `src/main/resources`
* One under `src/test/resources`.

# Local testing

## Run with Docker
### Requirements
- [Docker](https://docs.docker.com/) & [Docker Compose](https://docs.docker.com/compose/)
### Start the server
For starting the One Record Java Sandbox with a MongoDB, use the following command:
```bash
docker compose up -d
```
Docker will pull the MongoDB image and builds the One Record Server image (if your local machine has not already pulled and/or built it before).
Docker will then run both images in detached mode (-d Parameter).
The application should be available at http://localhost:8080.

### Stop the server
For stopping all running containers, use the following commmand:
```bash
docker-compose down
```

For stopping and removing all containers, networks, and all images used by any service in <em>docker-compose.yml</em> file, use the following command:
```bash
docker-compose down --rmi all
```

## Build & run the sandbox code
For testing the application locally, follow the next steps:
1. Install `Java = 8` and `Maven`. 
2. Install the Maven dependencies by running `mvn -Djdk.tls.client.protocols=TLSv1.2 clean install`. 
3. (Optional) Download the latest ONE Record Turtle ontologies from Github + the latest Web Access Control from W3C by running `mvn -Djdk.tls.client.protocols=TLSv1.2 exec:java`.
As the latest models are already generated and committed in this repository according to the latest ontologies, kindly skip steps 4, 5 and 6.
4. (SKIP) Generate ONE Record API related models by running `mvn package -Dbuild=api`
5. (SKIP) Generate ONE Record cargo related data model by running `mvn package -Dbuild=cargo`. Optionally remove directory `src/main/generated-sources/org/iata/cargo` prior generating.
6. (SKIP) Generate W3C Web Access Control ACL (Access Control List) related models by running `mvn package -Dbuild=acl`
7. MongoDB has updated their server, so in order to have it work properly, one needs to add an extra VM argument. The solution is actually change the TLS Version to 1.2 in JVM params.
`-Djdk.tls.client.protocols=TLSv1.2`
8. Run the server from OneRecordApplication or from the command line via `mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Djdk.tls.client.protocols=TLSv1.2"`. Your application should be available at [http://localhost:8080](http://localhost:8080).
9. The server can also be started by running the executable jar created in the `/target` folder after the `mvn clean install` command, via:
`   java -jar -Djdk.tls.client.protocols=TLSv1.2 one-record-server-java-1.0-SNAPSHOT.jar`
   
## Swagger API Documentation
Swagger API Documentation can be accessed and tested via [https://yourserverurl/swagger-ui.html](https://yourserverurl/swagger-ui.html). If you run the server locally, Swagger documentation can be accessed via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Postman collection
A Postman collection containing ONE Record API endpoints ready for testing can be found [here](https://github.com/IATA-Cargo/one-record-server-java/tree/master/src/test/resources/postman).

## JSON-LD models
JSON-LD generated files for the ONE Record Cargo related models, and the API specific models can be found [here](https://github.com/IATA-Cargo/ONE-Record/tree/master/working_draft/API/json-ld).
Some examples with test values can be also found [here](https://github.com/IATA-Cargo/one-record-server-java/tree/master/src/test/resources/examples).

# Generate Java Models from the ontologies with [JOPA](https://github.com/kbss-cvut/jopa)
The [JOPA](https://github.com/kbss-cvut/jopa) generated Java models can be found under `src/main/generated-sources`.
In order to generate the models, one should follow the steps below:

1. Download the latest ONE Record Turtle ontologies from GitHub + the latest Web Access Control from W3C by running `mvn exec:java`.
2. Generate ONE Record cargo related data model by running `mvn package -Dbuild=cargo`. Optionally remove directory `src/main/generated-sources/org/iata/cargo` prior generating.
3. Generate ONE Record API related models by running `mvn package -Dbuild=api`.
4. Generate W3C Web Access Control ACL (Access Control List) related models by running `mvn package -Dbuild=acl`.

Extra annotations were added in the Java model classes in order to improve the JSON-LD representation generated by JOPA. It is advised to compare the old version and new generated models via Git
in order to restore the extra annotations:
* `@Id(generated = true)` and `@ApiModelProperty(readOnly = true)` for the `id` field;
* `@JsonIgnore` for `name`, `description` and `properties` fields;
*  Extra `language` field added;
*  `@JsonProperty("@type")` and `@ApiModelProperty(allowableValues = Vocabulary.s_c_TheNameOfTheClass)` for `types` field;
* `@Document(collection = "nameOfTheMongoDBCollection")` for the models stored into MongoDB.

# Deployment
## MongoDB database 
ONE Record Server uses a MongoDB database. In order to setup quickly your own MongoDB environment, we suggest you to use a cloud database service such as [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).

1. To get started with MongoDB Atlas, [follow these steps](https://docs.atlas.mongodb.com/getting-started/). 
2. Add your database URL to `application.properties` file: `'spring.data.mongodb.uri': 'mongodb+srv://<username>:<password>@<databaseURI>/<databaseName>?retryWrites=true&w=majority'`

## Deployment to Heroku
You can deploy the ONE Record sandbox on the platform that you wish. We are providing you with simple steps to deploy the application on [Heroku platform](https://www.heroku.com/home). You can find the complete instructions on how to deploy a `Node.js` application on Heroku [here](https://devcenter.heroku.com/articles/deploying-nodejs).
1. Create a free [Heroku account](https://signup.heroku.com/signup/dc).
2. Download and install [Heroku CLI](https://cli.heroku.com/).
3. Login to Heroku via command line: `heroku login -i`
4. Checkout the Github repository
5. Create an app on Heroku via `heroku create`. The output of the command should look like:

> Creating app... done, ⬢ some-random-name
> https://some-random-name.herokuapp.com/ |
> https://git.heroku.com/some-random-name.git

 You can now visualize your app via [your Heroku dashboard](https://dashboard.heroku.com/apps).

7. Add your Heroku remote as a remote in your current repository via the following command:
`heroku git:remote -a some-random-name`
8. Push the code from the Github repository to newly created app: `git push heroku master`.
9. All set! Your server should be available at https://some-random-name.com.

# Security in ONE Record
By default, the security layer supporting mutual TLS is disabled in this sandbox.

# IATA ONE Record
You can find further information about ONE Record specifications on the [main Github repository](https://github.com/IATA-Cargo/ONE-Record), 
on the [ONE Record Developer Poral](http://www.onerecordcargo.org/index.html) and on the [IATA dedicated webpage](https://www.iata.org/en/programs/cargo/e/one-record/).


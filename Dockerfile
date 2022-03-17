### build image
FROM openjdk:8-slim as builder

# install maven
RUN apt-get update  
RUN apt-get install -y maven

# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build

# download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY . .

# install the Maven dependencies
RUN mvn -Djdk.tls.client.protocols=TLSv1.2 package -DskipTests


# run layer 
FROM openjdk:8-jre-slim as runtime
EXPOSE 8080

# set app home folder
ENV APP_HOME /app

# create base app folder
RUN mkdir $APP_HOME

WORKDIR $APP_HOME
# copy executable jar file from the builder image
COPY --from=builder /build/target/*.jar app.jar

ENTRYPOINT [ "java", "-Djdk.tls.client.protocols=TLSv1.2", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

### build image
FROM maven:3.8.6-amazoncorretto-18 as builder

# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build

# download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY . .

# install the Maven dependencies
RUN mvn package -DskipTests

# run layer
FROM amazoncorretto:18.0.2-alpine3.15 as runtime

## set app home folder
ENV APP_HOME /app

## create and set base app folder
RUN mkdir $APP_HOME
WORKDIR $APP_HOME

## copy executable jar file from the builder image
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]


### docker commands

1. docker --version
2. docker system prune
3. docker build -t dockerLogin/appName:version
e.g docker build -t darryl/vttp5a-ssf-day13l:0.0.1 . 

4. docker image ls //this shows the images
Run the image inside container
5. docker container -d -t -p 8080:3000 vttp5a-ssf-day13l:0.01
8080 is the port on the computer, 3000 is the port in the program/server that the real computer port will link to. so browser 8080 will access the 

## Important commands
//this is to remove cached docker builds
docker system prune

docker build -t jonahng/vttp5-ssf-day02ws:v.0.0.1 .
docker run -d -p 8080:3000 jonahng/vttp5-ssf-day02ws:v.0.0.1

## TO RUN ON RAILWAY MUST USE COMMAND PROMT TO RAILWAY LINK RAILWAY UP



FROM eclipse-temurin-23

LABEL MAINTAINER ="jonahng"
LABEL description ="This is vttp5 ssf day 3 lecture demo for dockerization"
LABEL name="vttp5_ssf_day02ws"

ARG APP_DIR=/APP_DIR


#Directory where source code will stay, and where your project will be copied
WORKDIR ${APP_DIR}

#Copying the required files into the image
COPY pom.xml .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn
#.mvn is a hidden folder that contains necessary things to run maven.


#Package the application using the RUN directive and download the dependencies in POM.xml, then compile into jar
RUN mvn package -Dmaven.test.skip=true

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar target/vttp5_ssf_day02w-0.0.1-SNAPSHOT.jar
#NAME comes from the pom.xml artifact id - version .jar
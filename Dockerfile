FROM java:8-jdk-alpine
COPY ./target/testservice-0.1.0.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","testservice-0.1.0.jar"]
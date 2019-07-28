DOCKER

# build docker image:
docker build -t testservice .
# run docker image:
docker run -p 8080:8080 --name service openlegacy/testservice
# stop container:
docker stop service
# remove container:
docker rm service

SWAGGER:
http://localhost:8080/swagger-ui.html

RUN LOCALLY:
mvn spring-boot:run
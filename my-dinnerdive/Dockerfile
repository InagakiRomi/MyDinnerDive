# define base docker image
FROM openjdk:11
LABEL maintainer="javaguides.net"
ADD target/my-dinnerdive-0.0.1-SNAPSHOT.jar my-dinnerdive.jar
ENTRYPOINT ["java", "-jar", "my-dinnerdive.jar"]
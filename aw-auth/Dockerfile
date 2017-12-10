FROM maven:3.5

# app directory
WORKDIR /aw-auth

# command line instructions
RUN mvn clean package
ADD target/aw-auth-0.0.1-SNAPSHOT.jar auth.jar

CMD ["java", "-jar", "./auth.jar"]
EXPOSE 8007

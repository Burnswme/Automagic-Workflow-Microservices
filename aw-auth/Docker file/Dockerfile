FROM java:8
WORKDIR /aw-auth
ADD target/aw-auth-0.0.1-SNAPSHOT.jar auth.jar

CMD ["java", "-jar", "./auth.jar"]
EXPOSE 8007
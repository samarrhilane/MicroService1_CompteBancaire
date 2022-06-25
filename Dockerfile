FROM openjdk:17
ADD target/CompteBancaire-0.0.1-SNAPSHOT.jar CompteBancaire-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","CompteBancaire-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
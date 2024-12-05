FROM openjdk:17
WORKDIR /app
COPY target/PruebaHazelcast-1.0-SNAPSHOT.jar /app/PruebaHazelcast-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "PruebaHazelcast-1.0-SNAPSHOT.jar"]

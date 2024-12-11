FROM openjdk:21-jdk-slim AS build
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY target/PruebaHazelcast-1.0-SNAPSHOT.jar /app/PruebaHazelcast.jar
CMD ["java", "-jar", "PruebaHazelcast.jar"]

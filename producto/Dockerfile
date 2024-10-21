# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/producto-0.0.1-SNAPSHOT.jar app2.jar

# Make port 9092 available to the world outside this container
EXPOSE 9092

# Run the jar file
ENTRYPOINT ["java", "-jar", "app2.jar"]
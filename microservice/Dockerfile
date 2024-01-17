# Use an official OpenJDK runtime as a parent image
FROM khipu/openjdk17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/microservice-0.0.1-SNAPSHOT.jar /app/

# Specify the command to run on container startup
CMD ["java", "-jar", "microservice-0.0.1-SNAPSHOT.jar"]

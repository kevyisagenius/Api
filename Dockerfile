# Use a lightweight Java runtime as a base image
FROM eclipse-temurin:21-jre-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file from your Maven target directory to the container
# Replace 'backend-0.0.1-SNAPSHOT.jar' if your JAR file has a different name
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that Spring Boot runs on (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

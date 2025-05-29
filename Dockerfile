# Use a lightweight Java runtime
FROM eclipse-temurin:21-jre

# Create a folder for the app
WORKDIR /app

# Copy the jar file into the image
COPY app.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

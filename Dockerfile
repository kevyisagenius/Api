# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set the working directory in the Maven container
WORKDIR /usr/src/app

# Copy the pom.xml file
COPY pom.xml .

# Copy the rest of your backend source code
COPY src ./src

# Package the application. The JAR will be in /usr/src/app/target/
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the executable JAR file from the builder stage
COPY --from=builder /usr/src/app/target/app.jar app.jar

# Expose the port that Spring Boot runs on (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
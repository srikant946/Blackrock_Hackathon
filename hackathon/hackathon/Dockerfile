# Step 1: Set the base image to a Linux distribution (Ubuntu-based OpenJDK image)
FROM openjdk:21

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file into the container
COPY ./target/hackathon-0.0.1-SNAPSHOT.jar /app/hackathon.jar

# Step 4: Expose the required port 5477
EXPOSE 5477

# Step 5: Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "/app/hackathon.jar"]

# Image name convention: blk-hacking-ind-{name-lastname}
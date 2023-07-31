FROM maven:3.8.3-openjdk-17 AS builder
#FROM maven:3.6.3-openjdk-8 AS builder
WORKDIR /build
# Copy only the pom.xml to leverage Docker cache, dependencies won't change as often as source code
COPY pom.xml .
# If there's settings.xml, copy to .m2 directory
#COPY settings.xml /root/.m2/settings.xml
RUN mvn dependency:go-offline -B
# Copy the source code and build the application
COPY src /build/src
RUN mvn clean package -DskipTests

#Application image
FROM maven:3.8.3-openjdk-17
WORKDIR /app
# ARG ARTIFACT_VERSION
COPY --from=builder /build/target/demo-1.0-SNAPSHOT.jar /app/final-artifact.jar
# Set the timezone in a more standard way
# ENV TZ='Tehran-3:30'
# RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
EXPOSE 8082
# Use ENTRYPOINT to allow passing arguments to your JAR
ENTRYPOINT ["java", "-jar", "/app/final-artifact.jar"]

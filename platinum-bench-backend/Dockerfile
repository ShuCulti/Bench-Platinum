FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

COPY gradlew ./
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src

RUN chmod +x gradlew && ./gradlew bootJar --no-daemon

FROM eclipse-temurin:25-jre
WORKDIR /app
RUN groupadd --system pb && useradd --system --gid pb --no-create-home pb
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
USER pb
ENTRYPOINT ["java", "-jar", "app.jar"]
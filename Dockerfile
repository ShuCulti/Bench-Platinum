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
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#I decided to go with jar instead of build because my ci already does test so testing twice is waste of compute
#And apparently it saves space to run a jar in the jre instead running with the entire jdk
#RUN ./gradlew build --no-daemon


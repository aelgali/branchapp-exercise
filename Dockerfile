FROM openjdk:17-slim



RUN mkdir -p app/gradle/

WORKDIR "/app"

COPY gradle/ app/gradle

COPY [".", "app/"]

RUN cd app/ && ./gradlew build

ENTRYPOINT cd app/ && ./gradlew bootRun

EXPOSE 8080


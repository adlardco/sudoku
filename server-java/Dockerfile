FROM openjdk:15-jdk-alpine AS build
ENV APP_HOME=/root/app/
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY src $APP_HOME/src
RUN mkdir -p $APP_HOME/src/main/resources/public
COPY --from=sudoku-ui:latest /src/build $APP_HOME/src/main/resources/public
RUN chmod +x ./gradlew
RUN ./gradlew --stacktrace test shadowJar

FROM openjdk:15-jdk-alpine
RUN apk add --update curl jq
RUN addgroup -S app && adduser -S app -G app
USER app
WORKDIR /home/app
COPY --from=build /root/app/build/libs/app.jar .
HEALTHCHECK --interval=10s --timeout=3s --retries=3 \
          CMD curl --silent --fail --request GET http://localhost:8080/api/info \
                | jq --exit-status '.status == "UP"' || exit 1
CMD java -jar /home/app/app.jar
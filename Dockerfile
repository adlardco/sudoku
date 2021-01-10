FROM alpine:3.12.1 AS BUILD_UI
RUN apk add --update npm
ENV APP_HOME=/root/app/
WORKDIR $APP_HOME
COPY ui/package.json ui/tsconfig.json $APP_HOME
COPY ui/src $APP_HOME/src
COPY ui/public $APP_HOME/public
RUN npm install
RUN npm test -- --watchAll=false
RUN npm run build

FROM openjdk:15-jdk-alpine AS BUILD_SERVER
ENV APP_HOME=/root/app/
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME
COPY server/build.gradle server/gradlew server/gradlew.bat server/settings.gradle $APP_HOME
COPY server/gradle $APP_HOME/gradle
COPY server/src $APP_HOME/src
RUN mkdir -p $APP_HOME/src/main/resources/public
COPY --from=BUILD_UI /root/app/build $APP_HOME/src/main/resources/public
RUN chmod +x ./gradlew
RUN ./gradlew --stacktrace test shadowJar

FROM openjdk:15-jdk-alpine
RUN apk add --update curl jq
RUN addgroup -S app && adduser -S app -G app
USER app
WORKDIR /home/app
COPY --from=BUILD_SERVER /root/app/build/libs/app.jar .
HEALTHCHECK --interval=10s --timeout=3s --retries=3 \
          CMD curl --silent --fail --request GET http://localhost:8080/api/info \
                | jq --exit-status '.status == "UP"' || exit 1
CMD java -jar /home/app/app.jar
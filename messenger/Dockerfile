FROM openjdk:17
ENV APPLICATION_JAR_FILE_NAME=messenger.jar
COPY ./target/$APPLICATION_JAR_FILE_NAME ./$APPLICATION_JAR_FILE_NAME
ENV APP_OPTS="-Duser.timezone=Europe/Kiev"
ENTRYPOINT exec java -Xdebug -Xrunjdwp:transport=dt_socket,address=*:8061,server=y,suspend=n $JAVA_OPTS $APP_OPTS -jar $APPLICATION_JAR_FILE_NAME

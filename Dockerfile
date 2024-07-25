FROM openjdk:17-jdk-slim
ENV APP_HOME=/opt/app_home
COPY target/player-service-*.jar $APP_HOME/app.jar
COPY src/main/resources/player.csv $APP_HOME/player.csv

RUN chmod 777 $APP_HOME/*
WORKDIR $APP_HOME
EXPOSE 8080
CMD ["java", "-Dplayers.csv-file.path=/opt/app_home/player.csv", "-jar", "app.jar"]
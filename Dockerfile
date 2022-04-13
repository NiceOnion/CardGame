FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY  index.html CardGame/html/webapp/

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]

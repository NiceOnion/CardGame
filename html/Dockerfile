FROM openjdk:8

EXPOSE 8080

ADD --chown=NiceOnion target/html-1.0.jar html-1.0.jar

ENTRYPOINT ["java" , "-jar", "build/html-1.0.jar"]

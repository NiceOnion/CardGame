FROM alpine:3.9

RUN apk add figlet

CMD ["figlet", "-w", "1000", "Hello, world!"]
$ docker build -t <your_account_name_here>/hello-world .

# V Old code V
#FROM openjdk:8-jre-slim as production

#EXPOSE 8080

#RUN mkdir /app

#COPY --from=build /app/target/spring-petclinic-*.jar /spring-petclinic.jar

#CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-petclinic.jar"]

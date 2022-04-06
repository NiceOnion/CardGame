FROM openjdk:13-alpine
RUN apk --no-chache add curl
Volume /tmp
ADD /target/*.jar
ENTRYPOINT ["java","-jar","/*.jar"]
EXPOSE 8080

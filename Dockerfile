FROM openjdk:8-jdk-alpine
EXPOSE 8986

VOLUME /tmp
ADD secdn-rapid.jar /secdn-rapid.jar
ENTRYPOINT ["java","-jar","/secdn-rapid.jar"]

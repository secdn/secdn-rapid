FROM openjdk:8
EXPOSE 8080

VOLUME /tmp
ADD secdn-rapid.jar /secdn-rapid.jar
RUN bash -c 'touch /secdn-rapid.jar'
ENTRYPOINT ["java","-jar","/secdn-rapid.jar"]

FROM openjdk:8
VOLUME /tmp
MAINTAINER Javaer
ADD /target/mybatisboot-0.0.1-SNAPSHOT.jar /test.jar
ENTRYPOINT ["java", "-Dport=8081", "-jar", "/test.jar"]
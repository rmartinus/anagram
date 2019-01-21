FROM openjdk:8-alpine
MAINTAINER rmartinus
RUN mkdir -p /opt/anagram
WORKDIR /opt/anagram
COPY build/libs/anagram-0.0.1-SNAPSHOT.jar /opt/anagram/reactive-anagram.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-Dspring.profiles.active=docker", "-jar","/opt/anagram/reactive-anagram.jar"]
EXPOSE 8080

FROM openjdk:8

RUN apt-get update -y \
  &&  apt-get install -y maven

ADD wellnr-crawler /opt/wellnr-crawler
ADD crawler.yml /opt/

RUN cd /opt/wellnr-crawler \
  && mvn package \
  && mv target/wellnr-crawler-1.0-SNAPSHOT.jar /opt/

EXPOSE 8080
WORKDIR /opt
CMD [ "java", "-jar", "wellnr-crawler-1.0-SNAPSHOT.jar", "server", "crawler.yml" ]
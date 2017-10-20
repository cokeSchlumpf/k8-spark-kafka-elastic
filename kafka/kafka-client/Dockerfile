FROM java:8

ADD wellnr-crawler-1.0-SNAPSHOT.jar crawler.yml /opt/

WORKDIR /opt/
CMD [ "java", "-jar", "wellnr-crawler-1.0-SNAPSHOT.jar", "server", "crawler.yml" ]
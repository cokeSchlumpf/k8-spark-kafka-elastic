FROM wellnr/spark-base

RUN apt-get update -y \
  &&  apt-get install -y maven

ADD wellnr-ingestion /opt/wellnr-ingestion

RUN cd /opt/wellnr-ingestion \
  && mvn package

CMD [ \
  "/opt/spark/bin/spark-submit", \
  "--master", "spark://spark:7077", \
  "/opt/wellnr-ingestion/target/wellnr-ingestion-1.0-SNAPSHOT.jar" ]
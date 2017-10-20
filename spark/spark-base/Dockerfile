FROM java:8

ENV \
  SPARK_HOME=/opt/spark \
  SPARK_NAME=spark-2.2.0-bin-hadoop2.7

RUN cd / \
  && curl -L -O https://d3kbcqa49mib13.cloudfront.net/${SPARK_NAME}.tgz \
  && tar -xvf ${SPARK_NAME}.tgz \
  && rm -rf ${SPARK_NAME}.tgz \
  && mv /${SPARK_NAME} /opt/ \
  && cd opt \
  && ln -s /opt/${SPARK_NAME} spark \
  && cd / \
  && groupadd spark \
  && useradd -M -g spark spark \
  && usermod --lock spark \
  && chown -R spark:spark ${SPARK_HOME}/

EXPOSE 8080 6066
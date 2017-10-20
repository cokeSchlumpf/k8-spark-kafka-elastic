FROM java:8

ENV \
  KAFKA_HOME=/opt/kafka \
  KAFKA_NAME=kafka_2.11-0.11.0.1

RUN cd / \
  && curl -L -O http://apache.mirror.digionline.de/kafka/0.11.0.1/${KAFKA_NAME}.tgz \
  && tar -xvf ${KAFKA_NAME}.tgz \
  && rm -rf ${KAFKA_NAME}.tgz \
  && mv /${KAFKA_NAME} /opt/ \
  && cd opt \
  && ln -s /opt/${KAFKA_NAME} kafka \
  && cd / \
  && groupadd kafka \
  && useradd -M -g kafka kafka \
  && usermod --lock kafka \
  && chown -R kafka:kafka ${KAFKA_HOME}/
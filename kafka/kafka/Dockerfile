FROM wellnr/kafka-base

EXPOSE 9092

ADD server.properties /opt/kafka/config/server.properties
USER kafka
CMD ["/opt/kafka/bin/kafka-server-start.sh", "/opt/kafka/config/server.properties"]
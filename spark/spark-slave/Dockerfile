FROM wellnr/spark-base

COPY manage.sh /usr/local/bin/
RUN chmod +rx /usr/local/bin/*.sh

USER spark
ENTRYPOINT ["manage.sh"]
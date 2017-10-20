FROM wellnr/spark-base

EXPOSE 7077

COPY manage.sh /usr/local/bin/
RUN chmod +rx /usr/local/bin/*.sh

USER spark
ENTRYPOINT ["manage.sh"]
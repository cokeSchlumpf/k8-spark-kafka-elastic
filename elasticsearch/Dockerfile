FROM java:8

ENV \
  ES_HOME=/opt/elasticsearch \
  ES_NAME=elasticsearch-5.6.1

RUN cd / \
  && curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/${ES_NAME}.tar.gz \
  && tar -xvf ${ES_NAME}.tar.gz \
  && rm -rf ${ES_NAME}.tar.gz \
  && mv /${ES_NAME} /opt/ \
  && cd opt \
  && ln -s /opt/${ES_NAME} elasticsearch \
  && cd / \
  && groupadd elasticsearch \
  && useradd -M -g elasticsearch elasticsearch \
  && usermod --lock elasticsearch \
  && chown -R elasticsearch:elasticsearch ${ES_HOME}/

ADD config/elasticsearch.yml ${ES_HOME}/config/elasticsearch.yml
ADD config/jvm.options ${ES_HOME}/config/jvm.options

RUN mkdir /data \
  && (mv ${ES_HOME}/data /data/ || mkdir /data/data) \
  && (mv ${ES_HOME}/log /data/ || mkdir /data/log) \
  && (mv ${ES_HOME}/work /data/ || mkdir /data/work) \
  && chown -R elasticsearch:elasticsearch /data/ \
  && chown -R elasticsearch:elasticsearch ${ES_HOME}/config/

EXPOSE 9200
EXPOSE 9300
WORKDIR /data
USER elasticsearch
CMD ["/opt/elasticsearch/bin/elasticsearch"]
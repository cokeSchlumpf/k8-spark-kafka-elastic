# Simple data-analytics-pipeline

This repository contains the realization of a simple data-science pipeline/ platform as Kubernetes cluster consisting of elasticsearch, kafka and spark.

## How to

To start the platform, 1st build the Docker containers. If you use minikube, use the minikube docker engine (`eval $(minikube docker-env)`). 

**In the current demo application, elasticsearch is not use, thus you can skip it.**

```bash
docker build elasticsearch/ -t wellnr/elasticsearch

docker build kafka/kafka-base -t wellnr/kafka-base
docker build kafka/kafka-client -t wellnr/kafka-client
docker build kafka/kafka-spark-stream -t wellnr/kafka-spark-stream
docker build kafka/kafka -t wellnr/kafka
docker build kafka/zookeeper -t wellnr/zookeeper

docker build spark/spark-base -t wellnr/spark-base
docker build spark/spark-master -t wellnr/spark-master
docker build spark/spark-slave -t wellnr/spark-slave
```

Aftewards the containers can be started in K8:

 ```bash
kubectl create -f elasticsearch/elastic.deployment.yml
kubectl create -f spark/spark.deployment.yml
kubectl create -f kafka/kafka.deployment.yml
 ```

As of now the application can be tested as follows:

1. If you're using minikube, get the URL of the `kafka-client` service:

```bash
minikube service kafka-client --url
# you may get: http://192.168.99.100:30180/
```

2. The client provides a simple service to put messages into a queue: 

```bash
curl -v http://192.168.99.100:30180/kafka-produce?topic=test&message=This%20is%20a%20message
```

3. Now you can check the logs of the `kafka-spark-stream` deployment, it should read from the `test` queue and count the words per message:

```bash
kubectl get pods # get the actual name of the running pod for kafka-spark stream
kubectl logs kafka-spark-stream-xxx -f # read the logs
```
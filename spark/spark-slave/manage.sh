#!/bin/bash

LOGFILE="/var/log"

start() {
  # Start spark and store log file location
  echo "Starting Spark Master ..."
  LOGFILE=`${SPARK_HOME}/sbin/start-slave.sh spark://spark:7077 | awk 'NF>1{print $NF}'`
}

monitor() {
  echo "Running - stop container to exit"

	# Loop forever by default - container must be stopped manually.
	while [ -z "$EXIT_CONTAINER" ]; do
    echo "Streaming log file ${LOGFILE}"
		tail -f ${LOGFILE}
	done

  echo "Received stopping signal. Stopping now."
  su -c "/opt/wlp/bin/server stop" wlp
  /sbin/service sshd stop
}

stop() {
  export EXIT_CONTAINER=1
}

start
trap stop SIGTERM SIGINT
monitor

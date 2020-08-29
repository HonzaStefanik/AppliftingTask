#!/usr/bin/env bash

if [ "$1" = 'run' ]; then
    exec java -Xms$XMS -Xmx$XMX \
        -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT \
        $JAVA_OPTS \
        -jar /var/lib/app/application.jar
else
    exec "$@"
fi

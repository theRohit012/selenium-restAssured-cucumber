#!/bin/bash

# Path to the Selenium Grid JAR
SELENIUM_JAR="selenium-server-4.31.0.jar"

# Choose the mode: standalone or hub
MODE=$1

if [[ "$MODE" == "hub" ]]; then
    java -jar $SELENIUM_JAR hub
elif [[ "$MODE" == "standalone" ]]; then
    java -jar $SELENIUM_JAR standalone
else
    echo "Usage: $0 [standalone|hub]"
fi


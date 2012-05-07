#!/bin/bash

# dirty
JAR=`ls ../../dist/YALL-*.jar | tail -n 1`

java -jar $JAR "$1"

#!/bin/bash

# dirty
JAR=`ls ../../dist/YALLC-*.jar | tail -n 1`

java -jar $JAR $*

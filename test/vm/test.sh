#!/bin/bash

# dirty
JAR=`ls ../../dist/YALLVM-*.jar | tail -n 1`

java -jar $JAR foo.vm

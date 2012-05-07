#!/bin/bash

# dirty
JAR=`ls ../../dist/YALL-*.jar | tail -n 1`

ls example* | grep -v \.help | while read filename; do
    echo
    echo ===== TEST $filename ========
    cat "$filename.help"
    echo

    java -jar $JAR "$filename"
done

ls *.yall | while read filename; do
    echo
    echo ===== $filename ======
    echo

    java -jar $JAR "$filename"
done

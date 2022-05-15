#!/bin/sh
mvn clean package && docker build -t com.mycompany/RomanArabicConverter .
docker rm -f RomanArabicConverter || true && docker run -d -p 9080:9080 -p 9443:9443 --name RomanArabicConverter com.mycompany/RomanArabicConverter
@echo off
call mvn clean package
call docker build -t com.mycompany/RomanArabicConverter .
call docker rm -f RomanArabicConverter
call docker run -d -p 9080:9080 -p 9443:9443 --name RomanArabicConverter com.mycompany/RomanArabicConverter
# Grafana - Graphite - Micrometer - Spring Boot - Starter

#### Install and start Grafana OSS
https://grafana.com/docs/grafana/latest/installation/debian/
#### Login as admin/admin
http://localhost:3000/login

#### Run Graphite in docker
https://graphite.readthedocs.io/en/stable/install.html

docker run -d \
 --name graphite \
 --restart=always \
 -p 80:80 \
 -p 2003-2004:2003-2004 \
 -p 2023-2024:2023-2024 \
 -p 8125:8125/udp \
 -p 8126:8126 \
 graphiteapp/graphite-statsd:1.1.7-8
 
#### Set up Grafana - Graphite data source
See step1.png, step2.png, step3.png, step4.png 
 
#### Run Spring Boot
./gradlew bootRun




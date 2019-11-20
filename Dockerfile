From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/spring-ready-app.war /usr/local/tomcat/webapps/spring-ready-app.war
CMD ["catalina.sh","run"]
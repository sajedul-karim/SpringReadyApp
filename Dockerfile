From tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./libs/springreadyapp.war /usr/local/tomcat/webapps/springreadyapp.war
CMD ["catalina.sh","run"]
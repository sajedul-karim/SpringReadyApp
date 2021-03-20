# SpringReadyApp
SpringBoot ready apps for demo in various article

After build just view http://IP/domaninName:PORT/springreadyapp/

Response will be:

{
  "serverName": "Server 1",
  "ipInfo": "Your current IP address : SKM-RND-0288/192.168.3.93\nYour current Hostname : SKM-RND-0288",
  "dateTime": "Wed Oct 16 14:50:35 BDT 2019"
}

#Swagger Home:
Replace Your host/IP and port
http://localhost:8081/springreadyapp/swagger-ui.html#/

#Redis Lock
Added code for redis lock for distributed system as example.
# Endpoints : 

springreadyapp/createTransactionNormal
springreadyapp/createTransactionLock
package com.appcoder.springreadyapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestController
public class MainController {

    @Autowired
    private Environment environment;

    @RequestMapping(method = RequestMethod.GET, value="/")
    public ResponseEntity<ResponseObject> hello(){
        ResponseObject responseObject=new ResponseObject();
        String serverName=environment.getProperty("machineName");

       String ipInfo=getIpInfo();

        responseObject.setServerName(serverName);
        responseObject.setIpInfo(ipInfo);
        responseObject.setDateTime(new Date().toString());
        return new ResponseEntity<>(
                responseObject,
                HttpStatus.OK);
    }

    private String getIpInfo(){

        String ipInfo="";

        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            ipInfo+=("Your current IP address : " + ip)+"\n";
            ipInfo+=("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();

            ipInfo+=e.getMessage();

        }

        return ipInfo;

    }

}

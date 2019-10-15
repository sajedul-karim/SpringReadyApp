package com.appcoder.springreadyapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class MainController {

    @Autowired
    private Environment environment;

    @RequestMapping(method = RequestMethod.GET, value="/")
    public String hello(){

        String response="";

        String serverName=environment.getProperty("machineName");

       String ipInfo=getIpInfo();

        response+="Server Name :"+serverName+"\n";
        response+="Ip Info :"+ipInfo;

        return response;
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

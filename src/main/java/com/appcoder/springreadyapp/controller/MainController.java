package com.appcoder.springreadyapp.controller;

import com.appcoder.springreadyapp.request.BasicRequest;
import com.appcoder.springreadyapp.response.BasicResponse;
import com.appcoder.springreadyapp.response.ResponseObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestController
@Api(tags = {"MainController"})
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

    @PostMapping(value = "/basicPost")
    public @ResponseBody
    ResponseEntity<BasicResponse> login(@RequestBody BasicRequest request)  {
        String response = "Your Name :"+request.getYourName()+" , Your Age :"+request.getYourAge();

        BasicResponse baseResponseObject = new BasicResponse(response, new Date().toString());

        return new ResponseEntity<>(baseResponseObject, HttpStatus.OK);

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

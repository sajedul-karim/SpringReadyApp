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
@RequestMapping("/main")
@Api(tags = {"MainController"})
public class MainController {

    @Autowired
    private Environment environment;

    @RequestMapping(method = RequestMethod.GET, value="/")
    public ResponseEntity<ResponseObject> hello() throws UnknownHostException {
        ResponseObject responseObject=new ResponseObject();

        InetAddress ip = InetAddress.getLocalHost();

        responseObject.setServerName(ip.getHostName());
        responseObject.setIpInfo("Your current IP address : " + ip);
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
}

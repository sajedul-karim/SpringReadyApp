package com.appcoder.springreadyapp.controller;

import com.appcoder.springreadyapp.factoryPattern.BaseUser;
import com.appcoder.springreadyapp.factoryPattern.UserCreationRequest;
import com.appcoder.springreadyapp.factoryPattern.UserFactory;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/designPattern")
@Api(tags = {"DesignPattern"})
public class DesignPatternController {

    @PostMapping(value = "/buildUser")
    public ResponseEntity<BaseUser> buildUser(HttpServletRequest requestHeader, @RequestBody UserCreationRequest request) throws RuntimeException {
        BaseUser baseUser = UserFactory.buildUser(request);
        return new ResponseEntity<>(baseUser, HttpStatus.OK);
    }
}

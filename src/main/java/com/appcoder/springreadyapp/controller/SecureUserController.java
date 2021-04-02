package com.appcoder.springreadyapp.controller;

import com.appcoder.springreadyapp.domain.User;
import com.appcoder.springreadyapp.request.springSecurity.LoginRequest;
import com.appcoder.springreadyapp.request.springSecurity.SignUpRequest;
import com.appcoder.springreadyapp.response.springSecurity.LoginResponse;
import com.appcoder.springreadyapp.response.springSecurity.UserResponse;
import com.appcoder.springreadyapp.services.ISecureUserService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/secureUsers")
@Api(tags = "SecureUserController")
public class SecureUserController {

    private ISecureUserService userService;

    public SecureUserController(ISecureUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/public/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest requestHeader, @RequestBody LoginRequest request) throws RuntimeException {

        LoginResponse loginResponse = userService.login(request.getUserName(), request.getPassword());
        if(loginResponse == null){
            throw new RuntimeException("Login failed. Possible cause : incorrect username/password");
        }else{
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/public/signUp")
    public ResponseEntity<User> signUp(HttpServletRequest requestHeader, @RequestBody SignUpRequest request) throws RuntimeException {

        User user;
        try {
            user = userService.signUp(request);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping(value = "deleteUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@RequestParam String userName) throws RuntimeException {
        try {
            userService.removeUser(userName);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }

    @GetMapping(value = "searchUser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<UserResponse> searchUser(@RequestParam String userName) throws RuntimeException {

        UserResponse userResponse = userService.searchUser(userName);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/refreshToken")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refreshToken(HttpServletRequest req) {
        return userService.refreshToken(req.getRemoteUser());
    }

}

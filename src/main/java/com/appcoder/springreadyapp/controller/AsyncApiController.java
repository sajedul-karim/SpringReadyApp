package com.appcoder.springreadyapp.controller;


import com.appcoder.springreadyapp.services.IAsyncProgrammingService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/asyncApi")
@Api(tags = {"AsyncApiController"})
public class AsyncApiController {
    private IAsyncProgrammingService asyncProgrammingService;

    public AsyncApiController(IAsyncProgrammingService asyncProgrammingService) {
        this.asyncProgrammingService = asyncProgrammingService;
    }

    @GetMapping(value = "/placeOrder")
    public ResponseEntity<Boolean> placeOrder() throws RuntimeException, ExecutionException, InterruptedException {
        return new ResponseEntity<>(asyncProgrammingService.placeOrder(1L, 2L), HttpStatus.OK);
    }

    @GetMapping(value = "/getUserFeeds")
    public ResponseEntity<String> getUserFeeds() throws RuntimeException, ExecutionException, InterruptedException {
        return new ResponseEntity<>(asyncProgrammingService.getUserFeeds("someemail@email.com"), HttpStatus.OK);
    }
}

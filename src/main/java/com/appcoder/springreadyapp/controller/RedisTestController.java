package com.appcoder.springreadyapp.controller;

import com.appcoder.springreadyapp.request.RedisCreateRequest;
import com.appcoder.springreadyapp.services.RedisCacheRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = {"RedisTestController"})
public class RedisTestController {

    @Autowired
    private RedisCacheRepository redisCacheRepository;

    @PostMapping(value = "/setKey")
    public ResponseEntity<String> setKey(HttpServletRequest requestHeader, @RequestBody RedisCreateRequest request) throws Exception {

        if(redisCacheRepository.acquireLock(request.getKey())){
            return new ResponseEntity<>("Lock acquire done", HttpStatus.OK);

        }else{
            return new ResponseEntity<>("Lock acquire failed, possible cause key already acquired", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/evictKey")
    public ResponseEntity<String> evictKey(HttpServletRequest requestHeader, @RequestBody RedisCreateRequest request) throws Exception {

        if(redisCacheRepository.releaseLock(request.getKey())){
            return new ResponseEntity<>("Lock release done", HttpStatus.OK);

        }else{
            return new ResponseEntity<>("Lock release failed, possible cause key not acquired YET", HttpStatus.OK);
        }
    }

}

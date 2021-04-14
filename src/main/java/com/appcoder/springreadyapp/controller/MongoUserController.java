package com.appcoder.springreadyapp.controller;

import com.appcoder.springreadyapp.domain.MongoUser;
import com.appcoder.springreadyapp.services.IMongoUserService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/mongoUser")
@Api(tags = {"MongoUserController"})
public class MongoUserController {

    private IMongoUserService mongoUserService;

    public MongoUserController(IMongoUserService mongoUserService) {
        this.mongoUserService = mongoUserService;
    }

    @PostMapping(value = "/saveUpdateUser")
    public ResponseEntity<String> saveUpdateUser(HttpServletRequest requestHeader, @RequestBody MongoUser request) throws RuntimeException {

        if (mongoUserService.saveUpdateUser(request)) {
            return new ResponseEntity<>("MongoUser Save/update Done", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("MongoUser Save/update failed", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/fetchAllCustomer")
    public ResponseEntity<List<MongoUser>> fetchAllCustomer() throws RuntimeException {
        return new ResponseEntity<>(mongoUserService.fetchAllUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/fetchCustomerByMobileNumber")
    public ResponseEntity<List<MongoUser>> fetchCustomerByMobileNumber(@RequestParam String mobileNumber) throws RuntimeException {
        return new ResponseEntity<>(mongoUserService.findUserByMobileNumber(mobileNumber), HttpStatus.OK);
    }

    @GetMapping(value = "/findUserByGenderNamePagination")
    public ResponseEntity<Page<MongoUser>> findUserByGenderNamePagination(@RequestParam String lastName, int pageId, int pageSize) throws RuntimeException {
        return new ResponseEntity<>(mongoUserService.findUserByGenderNamePagination(lastName, pageId, pageSize), HttpStatus.OK);
    }

    @PostMapping(value = "/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(HttpServletRequest requestHeader, @RequestBody MongoUser request) throws RuntimeException {

        if (mongoUserService.deleteUser(request)) {
            return new ResponseEntity<>("MongoUser delete Done", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("MongoUser delete failed", HttpStatus.OK);
        }
    }
}

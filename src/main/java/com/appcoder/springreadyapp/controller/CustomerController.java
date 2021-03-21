package com.appcoder.springreadyapp.controller;

import com.appcoder.springreadyapp.domain.Customer;
import com.appcoder.springreadyapp.services.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = {"CustomerController"})
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/saveCustomer")
    public ResponseEntity<String> saveCustomer(HttpServletRequest requestHeader, @RequestBody Customer request) throws RuntimeException {

        if (customerService.saveCustomer(request)) {
            return new ResponseEntity<>("Customer Save/update Done", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Customer Save/update failed", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/fetchAllCustomer")
    public ResponseEntity<List<Customer>> fetchAllCustomer() throws RuntimeException {
        return new ResponseEntity<>(customerService.fetchAllCustomer(), HttpStatus.OK);
    }

    @GetMapping(value = "/fetchCustomerByMobileNumber")
    public ResponseEntity<List<Customer>> fetchCustomerByMobileNumber(@RequestParam String mobileNumber) throws RuntimeException {
        return new ResponseEntity<>(customerService.findCustomerByMobileNumber(mobileNumber), HttpStatus.OK);
    }


    @PostMapping(value = "/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(HttpServletRequest requestHeader, @RequestBody Customer request) throws RuntimeException {

        if (customerService.deleteCustomer(request)) {
            return new ResponseEntity<>("Customer delete Done", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Customer delete failed", HttpStatus.OK);
        }
    }
}

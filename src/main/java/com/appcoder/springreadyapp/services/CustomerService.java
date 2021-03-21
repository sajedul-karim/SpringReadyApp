package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.domain.Customer;

import java.util.List;

public interface CustomerService {
    public boolean saveCustomer(Customer request);
    public boolean deleteCustomer(Customer request);
    public List<Customer> fetchAllCustomer();
    public List<Customer> findCustomerByMobileNumber(String mobileNumber);
}

package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.domain.Customer;
import com.appcoder.springreadyapp.domain.ICustomer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    public boolean saveUpdateCustomer(Customer request);

    public boolean deleteCustomer(Customer request);

    public List<Customer> fetchAllCustomer();

    public List<Customer> findCustomerByMobileNumber(String mobileNumber);
    public List<ICustomer> findCustomerByFirstNameProjection(String firstName);
    public List<Customer> findCustomerByFirstNameCustomQuery(String firstName);
    public Page<Customer> findCustomerByLastNamePagination(String lastName, int pageId, int pageSize);
}

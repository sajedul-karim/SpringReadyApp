package com.appcoder.springreadyapp;


import com.appcoder.springreadyapp.domain.Customer;
import com.appcoder.springreadyapp.exception.CustomerNotFoundException;
import com.appcoder.springreadyapp.repository.CustomerRepository;
import com.appcoder.springreadyapp.services.CustomerService;
import com.appcoder.springreadyapp.services.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceLayerTest {
    @Mock
    private CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void savedCustomer_Success() {
        Customer customer = new Customer();
        customer.setFirstName("sajedul");
        customer.setLastName("karim");
        customer.setMobileNumber("01737186095");

        // providing knowledge
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerRepository.save(customer);
        assertThat(savedCustomer.getFirstName()).isNotNull();
    }

    @Test
    public void savedCustomer_FirstName_exists_Failed() {
        Customer customer = new Customer();
        customer.setFirstName("sajedul");
        customer.setLastName("karim");
        customer.setMobileNumber("01737186095");

        // providing knowledge
        when(customerRepository.countAllByFirstName(customer.getFirstName())).thenReturn(1L);

        Boolean isSaveSuccess = customerService.saveUpdateCustomer(customer);
        assertThat(isSaveSuccess).isEqualTo(false);
    }

    @Test
    public void customer_exists_in_db_success() {
        Customer customer = new Customer();
        customer.setFirstName("sajedul");
        customer.setLastName("karim");
        customer.setMobileNumber("01737186095");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        // providing knowledge
        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> fetchedCustomers = customerService.fetchAllCustomer();
        assertThat(fetchedCustomers.size()).isGreaterThan(0);
    }

    @Test
    public void customer_search_found_success() {
        Customer customer = new Customer();
        customer.setFirstName("sajedul");
        customer.setLastName("karim");
        customer.setMobileNumber("01737186095");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        // providing knowledge
        when(customerRepository.findAllByMobileNumber(customer.getMobileNumber())).thenReturn(customerList);

        List<Customer> fetchedCustomers = customerService.findCustomerByMobileNumber(customer.getMobileNumber());
        assertThat(fetchedCustomers.size()).isGreaterThan(0);
    }

    @Test
    public void customer_search_not_found_failed() {
        Customer customer = new Customer();
        customer.setFirstName("sajedul");
        customer.setLastName("karim");
        customer.setMobileNumber("01737186095");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        List<Customer> fetchedCustomers = customerService.findCustomerByMobileNumber(customer.getMobileNumber());
        assertThat(fetchedCustomers.size()).isEqualTo(0);
    }

    @Test
    public void customer_search_not_found_exception_handle_failed() {

        Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            List<Customer> fetchedCustomers = customerService.findCustomerByMobileNumberException("018277272772");
            assertThat(fetchedCustomers.size()).isGreaterThan(0);
        });
    }

}

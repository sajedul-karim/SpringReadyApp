package com.appcoder.springreadyapp;


import com.appcoder.springreadyapp.domain.Customer;
import com.appcoder.springreadyapp.domain.ICustomer;
import com.appcoder.springreadyapp.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void initUseCase() {
        List<Customer> customers = Arrays.asList(
                new Customer("default", "user", "01737186090")
        );
        customerRepository.saveAll(customers);
    }

    @AfterEach
    public void destroyAll(){
        customerRepository.deleteAll();
    }

    @Test
    void saveAll_success() {
        List<Customer> customers = Arrays.asList(
                new Customer("sajedul", "karim", "01737186095"),
                new Customer("nafis", "khan", "01737186096"),
                new Customer("aayan", "karim", "01737186097")
        );
        Iterable<Customer> allCustomer = customerRepository.saveAll(customers);

        AtomicInteger validIdFound = new AtomicInteger();
        allCustomer.forEach(customer -> {
            if(customer.getId()>0){
                validIdFound.getAndIncrement();
            }
        });

        assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    void findAll_success() {
        List<Customer> allCustomer = customerRepository.findAll();
        assertThat(allCustomer.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findAllByFirstName_success() {
        List<ICustomer> allCustomer = customerRepository.findAllByFirstName("default");
        assertThat(allCustomer.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findAllByFirstName_failed() {
        List<ICustomer> allCustomer = customerRepository.findAllByFirstName("obama");
        assertThat(allCustomer.size()).isEqualTo(0);
    }

    @Test
    void findAllByMobile_success() {
        List<Customer> allCustomer = customerRepository.findAllByMobileNumber("01737186090");
        assertThat(allCustomer.size()).isEqualTo(1);
    }
}

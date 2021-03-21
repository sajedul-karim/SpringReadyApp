package com.appcoder.springreadyapp.repository;

import com.appcoder.springreadyapp.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();
    List<Customer> findAllByMobileNumber(String mobileNumber);

    void deleteById(Long id);

}

package com.appcoder.springreadyapp.repository;

import com.appcoder.springreadyapp.domain.Customer;
import com.appcoder.springreadyapp.domain.ICustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    List<Customer> findAllByMobileNumber(String mobileNumber);
    List<ICustomer> findAllByFirstName(String firstName);
    Page<Customer> findAllByLastName(String lastName, Pageable pageable);

    void deleteById(Long id);

}

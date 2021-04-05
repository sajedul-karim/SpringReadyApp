package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.domain.Customer;
import com.appcoder.springreadyapp.domain.ICustomer;
import com.appcoder.springreadyapp.exception.CustomerNotFoundException;
import com.appcoder.springreadyapp.repository.CustomerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static Log log = LogFactory.getLog(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean saveUpdateCustomer(Customer request) {
        Customer customer;

        if(customerRepository.countAllByFirstName(request.getFirstName())>0){
            log.error("First name already exists");
            return false;
        }

        if (request.getId() == null || request.getId() == 0) {
            customer = new Customer();
            customer.setFirstName(request.getFirstName());
            customer.setLastName(request.getLastName());
            customer.setMobileNumber(request.getMobileNumber());
            customer.setGender(request.getGender());
            customer.setPermanentAddress(request.getPermanentAddress());
            customer.setPresentAddress(request.getPresentAddress());
        } else {
            customer = request;
        }
        customerRepository.save(customer);
        return true;
    }

    @Override
    public boolean deleteCustomer(Customer request) {
        if (request.getId() == null || request.getId() == 0) {
            log.error("Invalid request");
            return false;
        }
        if (!customerRepository.existsById(request.getId())) {
            log.error("Customer not found based on provided data");
            return false;
        }
        customerRepository.deleteById(request.getId());
        return true;
    }

    @Override
    public List<Customer> fetchAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomerByMobileNumber(String mobileNumber) {
        return customerRepository.findAllByMobileNumber(mobileNumber);
    }

    @Override
    public List<Customer> findCustomerByMobileNumberException(String mobileNumber) throws CustomerNotFoundException {
        List<Customer> customers = customerRepository.findAllByMobileNumber(mobileNumber);
        if(customers == null || customers.size()==0 ){
            throw new CustomerNotFoundException("No customers found");
        }

        return customers;
    }

    @Override
    public List<ICustomer> findCustomerByFirstNameProjection(String firstName) {
        return customerRepository.findAllByFirstName(firstName);
    }

    @Override
    public List<Customer> findCustomerByFirstNameCustomQuery(String firstName) {
        String queryStr = "select id,first_name, last_name, mobile_number from customer where first_name = ?1";
        try {
            Query query = entityManager.createNativeQuery(queryStr);
            query.setParameter(1, firstName);

            List<Object[]> objectList = query.getResultList();

            List<Customer> result = new ArrayList<>();
            for (Object[] row : objectList) {
                result.add(new Customer(row));
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Page<Customer> findCustomerByLastNamePagination(String lastName, int pageId, int pageSize) {
        Pageable pageable = PageRequest.of(pageId, pageSize);
        return customerRepository.findAllByLastName(lastName,pageable);
    }

}

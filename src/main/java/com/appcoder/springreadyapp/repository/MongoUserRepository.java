package com.appcoder.springreadyapp.repository;

import com.appcoder.springreadyapp.domain.MongoUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {

    Long countAllByUserName(String userName);

    List<MongoUser> findAllByMobileNumber(String mobileNumber);

    Page<MongoUser> findAllByMobileNumber(String mobileNumber, Pageable pageable);
}

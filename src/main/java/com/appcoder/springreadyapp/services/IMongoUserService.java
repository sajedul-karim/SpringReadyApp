package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.domain.MongoUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMongoUserService {
    public boolean saveUpdateUser(MongoUser request);

    public boolean deleteUser(MongoUser request);

    public List<MongoUser> fetchAllUser();

    public List<MongoUser> findUserByMobileNumber(String mobileNumber);
    public Page<MongoUser> findUserByGenderNamePagination(String lastName, int pageId, int pageSize);
}

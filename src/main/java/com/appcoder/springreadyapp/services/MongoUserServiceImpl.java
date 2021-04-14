package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.domain.MongoUser;
import com.appcoder.springreadyapp.repository.MongoUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoUserServiceImpl implements IMongoUserService {
    private static Log log = LogFactory.getLog(MongoUserServiceImpl.class);

    private MongoUserRepository mongoUserRepository;

    public MongoUserServiceImpl(MongoUserRepository mongoUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
    }

    @Override
    public boolean saveUpdateUser(MongoUser request) {
        MongoUser mongoUser;

        if (request.getId() == null || request.getId().trim().length() == 0) {
            if(mongoUserRepository.countAllByUserName(request.getUserName())>0){
                log.error("UserName already exists");
                return false;
            }

            mongoUser = new MongoUser(request.getUserName(),request.getFullName(),request.getGender(), request.getMobileNumber());
        } else {
            mongoUser = request;
        }
        mongoUserRepository.save(mongoUser);
        return true;
    }

    @Override
    public boolean deleteUser(MongoUser request) {
        if (request.getId() == null || request.getId().trim().length() == 0) {
            log.error("Invalid request");
            return false;
        }
        if (!mongoUserRepository.existsById(request.getId())) {
            log.error("Customer not found based on provided data");
            return false;
        }
        mongoUserRepository.deleteById(request.getId());
        return true;
    }

    @Override
    public List<MongoUser> fetchAllUser() {
        return mongoUserRepository.findAll();
    }

    @Override
    public List<MongoUser> findUserByMobileNumber(String mobileNumber) {
        return mongoUserRepository.findAllByMobileNumber(mobileNumber);
    }


    @Override
    public Page<MongoUser> findUserByGenderNamePagination(String mobineNumber, int pageId, int pageSize) {
        Pageable pageable = PageRequest.of(pageId, pageSize);
        return mongoUserRepository.findAllByMobileNumber(mobineNumber,pageable);
    }
}

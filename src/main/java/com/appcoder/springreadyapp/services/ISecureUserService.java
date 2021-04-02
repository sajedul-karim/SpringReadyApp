package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.domain.User;
import com.appcoder.springreadyapp.request.springSecurity.SignUpRequest;
import com.appcoder.springreadyapp.response.springSecurity.LoginResponse;
import com.appcoder.springreadyapp.response.springSecurity.UserResponse;

public interface ISecureUserService {
    LoginResponse login(String username, String password);

    User signUp(SignUpRequest request);

    void removeUser(String username);

    UserResponse searchUser(String userName);

    String refreshToken(String userName);

}

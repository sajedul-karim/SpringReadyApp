package com.appcoder.springreadyapp.services;

import com.appcoder.springreadyapp.domain.User;
import com.appcoder.springreadyapp.exception.MyCustomException;
import com.appcoder.springreadyapp.repository.SecureUserRepository;
import com.appcoder.springreadyapp.request.springSecurity.SignUpRequest;
import com.appcoder.springreadyapp.response.springSecurity.LoginResponse;
import com.appcoder.springreadyapp.response.springSecurity.UserResponse;
import com.appcoder.springreadyapp.security.IJwtTokenProviderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SecureUserService implements ISecureUserService {
    private static Log log = LogFactory.getLog(SecureUserService.class);

    private SecureUserRepository secureUserRepository;

    private PasswordEncoder passwordEncoder;

    private IJwtTokenProviderService jwtTokenProviderService;
    private AuthenticationManager authenticationManager;


    public SecureUserService(SecureUserRepository secureUserRepository, PasswordEncoder passwordEncoder, IJwtTokenProviderService jwtTokenProviderService, AuthenticationManager authenticationManager) {
        this.secureUserRepository = secureUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProviderService = jwtTokenProviderService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse login(String userName, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = secureUserRepository.findByUsername(userName);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setEmail(user.getEmail());
            loginResponse.setUserName(user.getUserName());
            loginResponse.setAccessToken(jwtTokenProviderService.createToken(userName, user.getRoles()));

            log.info("Login successfully");

            return loginResponse;
        } catch (AuthenticationException e) {
            throw new MyCustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public User signUp(SignUpRequest request) {
        if(secureUserRepository.existsByUsername(request.getUserName())){
            throw new MyCustomException("User already exists in system", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());
        request.setPassword(user.getPassword());

        secureUserRepository.save(user);
        log.info("Register successfully");


        return user;
    }

    @Override
    public void removeUser(String userName) {
        if(!secureUserRepository.existsByUsername(userName)){
            throw new RuntimeException("User doesn't exists");
        }
        secureUserRepository.deleteByUsername(userName);
        log.info("User remove successfully");

    }

    @Override
    public UserResponse searchUser(String userName) {
        User user = secureUserRepository.findByUsername(userName);
        if (user == null) {
            throw new MyCustomException("Provided user doesn't exist", HttpStatus.NOT_FOUND);
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setUserName(user.getUserName());

        return userResponse;
    }

    @Override
    public String refreshToken(String userName) {
        return jwtTokenProviderService.createToken(userName, secureUserRepository.findByUsername(userName).getRoles());
    }
}

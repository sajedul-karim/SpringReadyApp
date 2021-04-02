package com.appcoder.springreadyapp.security;

import com.appcoder.springreadyapp.domain.User;
import com.appcoder.springreadyapp.repository.SecureUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private SecureUserRepository secureUserRepository;

    public MyUserDetailsService(SecureUserRepository secureUserRepository) {
        this.secureUserRepository = secureUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final User user = secureUserRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + userName + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(userName)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
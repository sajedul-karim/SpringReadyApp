package com.appcoder.springreadyapp.security;

import com.appcoder.springreadyapp.domain.Role;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IJwtTokenProviderService {
    String createToken(String username, List<Role> roles);
    Authentication getAuthentication(String token);
    String getUsername(String token);
    String resolveToken(HttpServletRequest req);
    boolean validateToken(String token);

}

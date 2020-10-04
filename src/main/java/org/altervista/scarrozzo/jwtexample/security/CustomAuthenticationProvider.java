package org.altervista.scarrozzo.jwtexample.security;

import org.altervista.scarrozzo.jwtexample.domain.CustomUser;
import org.altervista.scarrozzo.jwtexample.mapper.SecurityUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static Logger logger = LoggerFactory.getLogger(TokenManager.class);

    private boolean externalAuthExample(String username, String password){
        // TODO: call external service with username and password and return true if they are correct.

        // we are using a constant only for testing purpose
        return username.equalsIgnoreCase("sergio.carrozzo2@gmail.com");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomUser user = new CustomUser();
        user.setEmail(email);
        user.setPassword(password);

        // call to third party authentication system
        // if ok return UsernamePasswordAuthenticationToken
        // otherwise throw a BadCredentialsException
        if(externalAuthExample(email, password)){
            return new UsernamePasswordAuthenticationToken(SecurityUserMapper.toAuthenticationToken(user), password, Collections.emptyList());
        }else {
            logger.info("BadCredentialsException using custom authentication provider");
            throw new BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}

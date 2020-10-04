package org.altervista.scarrozzo.jwtexample.mapper;

import org.altervista.scarrozzo.jwtexample.domain.CustomUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class SecurityUserMapper {
    public static User toUser(CustomUser user) {
        return new User(user.getEmail(), user.getPassword(), List.of());
    }

    public static UsernamePasswordAuthenticationToken toAuthenticationToken(CustomUser user) {
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), List.of());
    }
}

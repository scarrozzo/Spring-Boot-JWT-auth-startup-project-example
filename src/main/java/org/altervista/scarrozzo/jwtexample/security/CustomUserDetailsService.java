package org.altervista.scarrozzo.jwtexample.security;

import org.altervista.scarrozzo.jwtexample.mapper.SecurityUserMapper;
import org.altervista.scarrozzo.jwtexample.repository.CustomUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final String PLACEHOLDER = UUID.randomUUID().toString();
    private static final User DEFAULT_USER = new User(PLACEHOLDER, PLACEHOLDER, List.of());
    private final CustomUserRepository repository;

    public CustomUserDetailsService(CustomUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .map(SecurityUserMapper::toUser)
                .orElse(DEFAULT_USER);
    }
}

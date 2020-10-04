package org.altervista.scarrozzo.jwtexample.repository;

import org.altervista.scarrozzo.jwtexample.domain.CustomUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {
    Optional<CustomUser> findByEmail(String email);
}

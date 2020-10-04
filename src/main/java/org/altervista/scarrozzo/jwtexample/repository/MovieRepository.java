package org.altervista.scarrozzo.jwtexample.repository;

import org.altervista.scarrozzo.jwtexample.domain.Movie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}

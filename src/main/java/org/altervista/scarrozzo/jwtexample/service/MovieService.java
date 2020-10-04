package org.altervista.scarrozzo.jwtexample.service;

import org.altervista.scarrozzo.jwtexample.domain.Movie;
import org.springframework.stereotype.Service;

public interface MovieService {
   Iterable<Movie> getAllMovies();
}

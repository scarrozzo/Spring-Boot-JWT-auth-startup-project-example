package org.altervista.scarrozzo.jwtexample.service.impl;

import org.altervista.scarrozzo.jwtexample.domain.Movie;
import org.altervista.scarrozzo.jwtexample.repository.MovieRepository;
import org.altervista.scarrozzo.jwtexample.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}

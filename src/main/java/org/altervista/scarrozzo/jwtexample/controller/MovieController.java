package org.altervista.scarrozzo.jwtexample.controller;

import org.altervista.scarrozzo.jwtexample.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    private ServerResponse getAllMovies(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(movieService.getAllMovies());
    }

    @Bean
    public RouterFunction<ServerResponse> bookRoutes() {
        return route()
                .nest(path("/movie"),
                        builder -> builder
                                .GET("/", this::getAllMovies))
                .build();
    }
}

package com.bnta.spring_cinema.controllers;


import com.bnta.spring_cinema.models.Movie;
import com.bnta.spring_cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

// There should be an autowiring of something here i think.

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Optional<List<Movie>>> getMovies(){
        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable long id){
        return new ResponseEntity<>(movieService.getMovie(id), HttpStatus.OK);
    }


}

package com.bnta.spring_cinema.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

// There should be an autowiring of something here i think.


    @PostMapping
    public ResponseEntity<String> createMoviesTable(){
        String isMoviesTableCreated = "Movie table is created";
        return new ResponseEntity<>(isMoviesTableCreated, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<String> getMovies(){
        String movieList = "Placeholder Should return all movies?";
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getMovie(){
        String movie = "Placeholder should return a movie by id";
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


}

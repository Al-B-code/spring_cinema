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
    public ResponseEntity<Optional<List<Movie>>> getMovies(@RequestParam(
            name = "MaxDuration",
            required = false,
            defaultValue = "300") int maxDuration){
        return new ResponseEntity<>(movieService.getMovies(maxDuration), HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<Optional<List<Movie>>> getMovies(@RequestParam(name = "MaxDuration", required = false, defaultValue = "300") int maxDuration){
//        return new ResponseEntity<>(movieService.getMovies(maxDuration), HttpStatus.OK);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id){
        Optional<Movie> optionalMovie = movieService.getMovie(id);
        if(optionalMovie.isEmpty()){
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalMovie.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
        public ResponseEntity<String> removeMovie(@PathVariable long id){
            return new ResponseEntity<>(movieService.removeMovie(id), HttpStatus.OK);
        }

    @PutMapping(value = "/{id}")
        public ResponseEntity<String> modifyMovie(@PathVariable long id, @RequestBody String title){
            return new ResponseEntity<>(movieService.modifyMovieName(id, title), HttpStatus.OK);
        }
}

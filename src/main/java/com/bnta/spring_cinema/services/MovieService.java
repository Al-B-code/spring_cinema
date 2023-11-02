package com.bnta.spring_cinema.services;

import com.bnta.spring_cinema.models.Movie;
import com.bnta.spring_cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {


    @Autowired
    MovieRepository movieRepository;




    public MovieService() {
    }


    public String addMovie(Movie movie){
        movie = new Movie(movie.getTitle(), movie.getRating(), movie.getDuration());
        movieRepository.save(movie);
        return "Movie Added";
    }

    public Optional<Movie> getMovie(long id){
        return movieRepository.findById(id);
    }

    public Optional<List<Movie>> getMovies(){
        return Optional.of(movieRepository.findAll());
    }

}

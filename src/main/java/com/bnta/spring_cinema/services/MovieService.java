package com.bnta.spring_cinema.services;

import com.bnta.spring_cinema.models.Movie;
import com.bnta.spring_cinema.repositories.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    public Optional<List<Movie>> getMovies(int maxDuration){
        List<Movie> movieList = movieRepository.findAll();
        ArrayList<Movie> maxDurationList = new ArrayList<>(); {
        }
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getDuration() < maxDuration) {
                maxDurationList.add(movieList.get(i));
            }
        }
        return Optional.of(maxDurationList);
    }

    public String removeMovie(long id){
        movieRepository.delete(movieRepository.getReferenceById(id));
        return "Movie removed";
    }

//    public String modifyMovieName(long id, String title){
//        Movie movie = movieRepository.getReferenceById(id);
//        movie.setTitle(title);
//        movieRepository.save(movie);
//        return "movie title changed";
//    }
public String modifyMovieName(long id, String titleJson) {
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(titleJson);
        String newTitle = jsonNode.get("title").asText();

        Movie movie = movieRepository.getReferenceById(id);
        movie.setTitle(newTitle);
        movieRepository.save(movie);

        return "Movie title changed";
    } catch (Exception e) {
        // Handle JSON parsing or other exceptions
        return "Error: " + e.getMessage();
    }
}


}

package com.sayant.jpaaudit.controller;

import com.sayant.jpaaudit.domain.Genre;
import com.sayant.jpaaudit.domain.Movie;
import com.sayant.jpaaudit.repositories.MovieRepository;
import com.sayant.jpaaudit.service.ReadingHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Juanma Perales on 4/12/21
 */
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;
    private final ReadingHistory service;

    @GetMapping
    public List<Movie> getMovies() {
        createDefaultMovie();
        return movieRepository.findAll();
    }

    @RequestMapping(value = "/default", method = RequestMethod.POST)
    private void createDefaultMovie() {
        String name = "Murder on the Orient Express";
        Movie movie = new Movie();
        movie.setName(name);
        movie.setYear(2001);
        movie.setGenre(Genre.DRAMA);
        movieRepository.save(movie);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createMovie(@RequestBody Movie movie) {
        Movie save = movieRepository.save(movie);
        return new ResponseEntity<>(save.getId(), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{movieId}", method = RequestMethod.PUT)
    public ResponseEntity<Long> updateMovie(@PathVariable Long movieId, @RequestBody Movie movie) {
        Movie saved = new Movie();
        Optional<Movie> byId = movieRepository.findById(movieId);
        if (byId.isPresent()) {
            Movie movieBBDD = byId.get();
            movieBBDD.setGenre(movie.getGenre());
            movieBBDD.setName(movie.getName());
            movieBBDD.setYear(movie.getYear());
            saved = movieRepository.save(movieBBDD);
        }

        return new ResponseEntity<Long>(saved.getId(), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/history")
    public List<Movie> getMovieHistory() {
        return service.getLastMovieChanges();
    }

    @GetMapping(value = "/updates")
    public ResponseEntity<List<Movie>> getMovieUpdates() {
        return new ResponseEntity<>(service.getMovieHistoricalChanges(), HttpStatus.OK);
    }
}

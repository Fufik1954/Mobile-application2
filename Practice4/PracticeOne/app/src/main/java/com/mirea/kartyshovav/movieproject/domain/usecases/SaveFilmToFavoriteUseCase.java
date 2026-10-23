package com.mirea.kartyshovav.movieproject.domain.usecases;

import com.mirea.kartyshovav.movieproject.domain.models.Movie;
import com.mirea.kartyshovav.movieproject.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {
    private MovieRepository movieRepository;

    public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie) {
        if (movie.getName().isEmpty()) {
            return false;
        }
        return movieRepository.saveMovie(movie);
    }
}
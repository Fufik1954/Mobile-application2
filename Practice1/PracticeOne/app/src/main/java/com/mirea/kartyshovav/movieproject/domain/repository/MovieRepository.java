package com.mirea.kartyshovav.movieproject.domain.repository;

import com.mirea.kartyshovav.movieproject.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
}
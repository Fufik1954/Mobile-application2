package com.mirea.kartyshovav.movieproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.mirea.kartyshovav.movieproject.domain.models.Movie;
import com.mirea.kartyshovav.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    private static final String PREFS_NAME = "movie_prefs";
    private static final String KEY_MOVIE_NAME = "favorite_movie_name";

    private final Context context;

    public MovieRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_MOVIE_NAME, movie.getName()).apply();
        return true;
    }

    @Override
    public Movie getMovie() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String name = prefs.getString(KEY_MOVIE_NAME, "Нет данных");
        return new Movie(1, name);
    }
}
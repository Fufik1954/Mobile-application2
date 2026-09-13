package com.mirea.kartyshovav.movieproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.kartyshovav.movieproject.data.repository.MovieRepositoryImpl;
import com.mirea.kartyshovav.movieproject.domain.models.Movie;
import com.mirea.kartyshovav.movieproject.domain.repository.MovieRepository;
import com.mirea.kartyshovav.movieproject.domain.usecases.GetFavoriteFilmUseCase;
import com.mirea.kartyshovav.movieproject.domain.usecases.SaveFilmToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Находим элементы UI
        EditText editTextMovie = findViewById(R.id.editTextMovie);
        TextView textViewMovie = findViewById(R.id.textViewMovie);
        Button buttonGetMovie = findViewById(R.id.buttonGetMovie);
        Button buttonSaveMovie = findViewById(R.id.buttonSaveMovie);

        // 2. Создаём реализацию репозитория (Data-слой)
        MovieRepository movieRepository = new MovieRepositoryImpl(this);
        // 3. Обработчик кнопки "Сохранить любимый фильм"
        buttonSaveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаём UseCase, передавая ему репозиторий
                SaveFilmToFavoriteUseCase saveUseCase =
                        new SaveFilmToFavoriteUseCase(movieRepository);

                // Создаём фильм из текста, который ввёл пользователь
                Movie movie = new Movie(2, editTextMovie.getText().toString());

                // Вызываем UseCase
                boolean result = saveUseCase.execute(movie);

                // Показываем результат
                textViewMovie.setText(String.format("Save result %s", result));
            }
        });

        // 4. Обработчик кнопки "Отобразить любимый фильм"
        buttonGetMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаём UseCase, передавая ему репозиторий
                GetFavoriteFilmUseCase getUseCase =
                        new GetFavoriteFilmUseCase(movieRepository);

                // Вызываем UseCase
                Movie movie = getUseCase.execute();

                // Показываем результат
                textViewMovie.setText(String.format("Favorite movie: %s", movie.getName()));
            }
        });
    }
}
package ru.mirea.kartyshovav.catcatalog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.kartyshovav.catcatalog.data.repository.CatBreedRepositoryImpl;
import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.GetCatBreedByIdUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.GetCatBreedsUseCase;

public class MainActivity extends AppCompatActivity {

    private CatBreedRepository repository;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Создаём реализацию репозитория (Data-слой)
        repository = new CatBreedRepositoryImpl();

        // 2. Находим элементы UI
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonAll = findViewById(R.id.buttonAll);
        Button buttonById = findViewById(R.id.buttonById);
        EditText editTextId = findViewById(R.id.editTextId);

        // 3. Обработчик кнопки "Показать все породы"
        buttonAll.setOnClickListener(v -> {
            GetCatBreedsUseCase useCase = new GetCatBreedsUseCase(repository);
            List<CatBreed> breeds = useCase.execute();

            StringBuilder sb = new StringBuilder();
            for (CatBreed breed : breeds) {
                sb.append("• ").append(breed.getName())
                        .append(" (").append(breed.getId()).append(")\n")
                        .append(breed.getDescription()).append("\n\n");
            }
            textViewResult.setText(sb.toString());
        });

        // 4. Обработчик кнопки "Найти по id"
        buttonById.setOnClickListener(v -> {
            String id = editTextId.getText().toString().trim();
            GetCatBreedByIdUseCase useCase = new GetCatBreedByIdUseCase(repository);
            CatBreed breed = useCase.execute(id);

            if (breed != null) {
                textViewResult.setText(
                        "Name: " + breed.getName() + "\n" +
                                "ID: " + breed.getId() + "\n" +
                                "Description: " + breed.getDescription() + "\n" +
                                "Image URL: " + breed.getImageUrl() + "\n" +
                                "Is Favorite: " + breed.isFavorite()
                );
            } else {
                textViewResult.setText("Порода с id \"" + id + "\" не найдена");
            }
        });
    }
}
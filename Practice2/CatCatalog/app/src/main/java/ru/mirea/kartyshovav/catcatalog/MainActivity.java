package ru.mirea.kartyshovav.catcatalog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.kartyshovav.catcatalog.data.network.mock.MockApi;
import ru.mirea.kartyshovav.catcatalog.data.repository.CatBreedRepositoryImpl;
import ru.mirea.kartyshovav.catcatalog.data.storage.CatBreedStorage;
import ru.mirea.kartyshovav.catcatalog.data.storage.room.RoomCatBreedStorage;
import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.catbreeds.GetCatBreedByIdUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.catbreeds.GetCatBreedsUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.favorites.SaveToFavoritesUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.favorites.GetFavoritesUseCase;

import ru.mirea.kartyshovav.catcatalog.data.auth.FirebaseAuthRepositoryImpl;
import ru.mirea.kartyshovav.catcatalog.domain.repository.AuthRepository;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.auth.LogoutUseCase;

import ru.mirea.kartyshovav.catcatalog.domain.network.NetworkApi;

public class MainActivity extends AppCompatActivity {

    private CatBreedRepository repository;
    private AuthRepository authRepository;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаём реализацию репозитория
        NetworkApi networkApi = new MockApi();
        CatBreedStorage storage = new RoomCatBreedStorage(this);
        repository = new CatBreedRepositoryImpl(networkApi, storage);
        authRepository = new FirebaseAuthRepositoryImpl();

        textViewResult = findViewById(R.id.textViewResult);
        Button buttonAll = findViewById(R.id.buttonAll);
        Button buttonById = findViewById(R.id.buttonById);
        EditText editTextId = findViewById(R.id.editTextId);
        Button buttonSaveFavorite = findViewById(R.id.buttonSaveFavorite);
        Button buttonShowFavorites = findViewById(R.id.buttonShowFavorites);


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

        buttonById.setOnClickListener(v -> {
            String id = editTextId.getText().toString().trim();
            GetCatBreedByIdUseCase useCase = new GetCatBreedByIdUseCase(repository);
            CatBreed breed = useCase.execute(id);

            if (breed != null) {
                textViewResult.setText(
                        "Имя: " + breed.getName() + "\n" +
                                "ID: " + breed.getId() + "\n" +
                                "Описание: " + breed.getDescription() + "\n" +
                                "Ссылка на фото: " + breed.getImageUrl() + "\n"
                );
            } else {
                textViewResult.setText("Порода с id \"" + id + "\" не найдена");
            }
        });

        buttonSaveFavorite.setOnClickListener(v -> {
            String id = editTextId.getText().toString().trim();

            if (id.isEmpty()) {
                textViewResult.setText("Введите id породы");
                return;
            }

            CatBreed breed = repository.getCatBreedById(id);
            if (breed == null) {
                textViewResult.setText("Порода с id \"" + id + "\" не найдена");
                return;
            }

            SaveToFavoritesUseCase useCase = new SaveToFavoritesUseCase(repository);
            boolean result = useCase.execute(breed);

            textViewResult.setText("Сохранено: " + result + " (" + breed.getName() + ")");
            editTextId.setText("");
        });


        buttonShowFavorites.setOnClickListener(v -> {
            GetFavoritesUseCase useCase = new GetFavoritesUseCase(repository);
            List<CatBreed> favorites = useCase.execute();

            if (favorites.isEmpty()) {
                textViewResult.setText("Избранное пусто");
                return;
            }

            StringBuilder sb = new StringBuilder("Избранное:\n");
            for (CatBreed breed : favorites) {
                sb.append("• ").append(breed.getName())
                        .append(" (").append(breed.getId()).append(")\n")
                        .append(breed.getDescription()).append("\n\n");
            }
            textViewResult.setText(sb.toString());
        });


        Button buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(v -> {
            LogoutUseCase logoutUseCase = new LogoutUseCase(authRepository);
            logoutUseCase.execute();

            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
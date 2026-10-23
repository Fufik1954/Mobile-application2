package ru.mirea.kartyshovav.catcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private TextView textViewResult;
    private EditText editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewModel через Factory
        viewModel = new ViewModelProvider(this, new MainViewModelFactory(this))
                .get(MainViewModel.class);

        textViewResult = findViewById(R.id.textViewResult);
        editTextId = findViewById(R.id.editTextId);
        Button buttonAll = findViewById(R.id.buttonAll);
        Button buttonById = findViewById(R.id.buttonById);
        Button buttonSaveFavorite = findViewById(R.id.buttonSaveFavorite);
        Button buttonShowFavorites = findViewById(R.id.buttonShowFavorites);
        Button buttonCombined = findViewById(R.id.buttonCombined);
        Button buttonLogout = findViewById(R.id.buttonLogout);

        // Подписка на resultLiveData
        viewModel.getResultLiveData().observe(this, result -> {
            textViewResult.setText(result);
        });

        // Подписка на MediatorLiveData
        viewModel.getCombinedLiveData().observe(this, combinedResult -> {
            textViewResult.setText(combinedResult);
        });

        buttonAll.setOnClickListener(v -> viewModel.loadCatBreeds());

        buttonById.setOnClickListener(v -> {
            String id = editTextId.getText().toString().trim();
            viewModel.loadCatBreedById(id);
        });

        buttonSaveFavorite.setOnClickListener(v -> {
            String id = editTextId.getText().toString().trim();
            viewModel.saveToFavorites(id);
            editTextId.setText("");
        });

        buttonShowFavorites.setOnClickListener(v -> viewModel.loadFavorites());

        // Объединённые данные
        buttonCombined.setOnClickListener(v -> viewModel.loadCombinedData());

        buttonLogout.setOnClickListener(v -> {
            viewModel.logout();
            Toast.makeText(this, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
package ru.mirea.kartyshovav.catcatalog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private CatBreedAdapter adapter;
    private TextView textViewResult;
    private EditText editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new MainViewModelFactory(this))
                .get(MainViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCatBreeds);
        adapter = new CatBreedAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewResult = findViewById(R.id.textViewResult);
        editTextId = findViewById(R.id.editTextId);

        // Подписка на  RecyclerView
        viewModel.getCatBreedsLiveData().observe(this, breeds -> {
            adapter.setItems(breeds);
        });

        // Подписка на LiveData
        viewModel.getResultLiveData().observe(this, result -> {
            textViewResult.setText(result);
        });

         // Подписка на MediatorLiveData
        viewModel.getCombinedLiveData().observe(this, combinedResult -> {
            textViewResult.setText(combinedResult);
        });

        Button buttonAll = findViewById(R.id.buttonAll);
        Button buttonById = findViewById(R.id.buttonById);
        Button buttonSaveFavorite = findViewById(R.id.buttonSaveFavorite);
        Button buttonShowFavorites = findViewById(R.id.buttonShowFavorites);
        Button buttonLogout = findViewById(R.id.buttonLogout);
        Button buttonCombined = findViewById(R.id.buttonCombined);

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
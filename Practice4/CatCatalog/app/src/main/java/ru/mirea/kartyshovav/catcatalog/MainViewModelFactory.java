package ru.mirea.kartyshovav.catcatalog;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.kartyshovav.catcatalog.data.auth.FirebaseAuthRepositoryImpl;
import ru.mirea.kartyshovav.catcatalog.data.network.mock.MockApi;
import ru.mirea.kartyshovav.catcatalog.data.repository.CatBreedRepositoryImpl;
import ru.mirea.kartyshovav.catcatalog.data.storage.CatBreedStorage;
import ru.mirea.kartyshovav.catcatalog.data.storage.room.RoomCatBreedStorage;
import ru.mirea.kartyshovav.catcatalog.domain.network.NetworkApi;
import ru.mirea.kartyshovav.catcatalog.domain.repository.AuthRepository;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public MainViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // Создаём зависимости
        NetworkApi networkApi = new MockApi();
        CatBreedStorage storage = new RoomCatBreedStorage(context);
        CatBreedRepository catBreedRepository = new CatBreedRepositoryImpl(networkApi, storage);
        AuthRepository authRepository = new FirebaseAuthRepositoryImpl();

        // Создаём ViewModel
        return (T) new MainViewModel(catBreedRepository, authRepository);
    }
}
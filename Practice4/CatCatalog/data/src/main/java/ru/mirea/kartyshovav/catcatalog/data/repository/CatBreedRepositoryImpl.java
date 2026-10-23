package ru.mirea.kartyshovav.catcatalog.data.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.mirea.kartyshovav.catcatalog.data.storage.CatBreedStorage;
import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.network.NetworkApi;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class CatBreedRepositoryImpl implements CatBreedRepository {

    private final NetworkApi networkApi;
    private final CatBreedStorage storage;

    public CatBreedRepositoryImpl(NetworkApi networkApi, CatBreedStorage storage) {
        this.networkApi = networkApi;
        this.storage = storage;
    }

    @Override
    public List<CatBreed> getCatBreeds() {
        return networkApi.getBreeds();
    }

    @Override
    public CatBreed getCatBreedById(String id) {
        return networkApi.getBreedById(id);
    }

    @Override
    public boolean saveToFavorites(CatBreed catBreed) {
        if (catBreed == null) return false;

        ru.mirea.kartyshovav.catcatalog.data.storage.models.CatBreed storageModel =
                mapToStorage(catBreed);

        return storage.save(storageModel);
    }

    @Override
    public List<CatBreed> getFavorites() {
        List<ru.mirea.kartyshovav.catcatalog.data.storage.models.CatBreed> storageModels =
                storage.getAll();

        List<CatBreed> result = new ArrayList<>();
        for (ru.mirea.kartyshovav.catcatalog.data.storage.models.CatBreed s : storageModels) {
            result.add(new CatBreed(
                    s.getId(),
                    s.getName(),
                    s.getImageUrl(),
                    s.getDescription()
            ));
        }
        return result;
    }

    // Маппер

    private ru.mirea.kartyshovav.catcatalog.data.storage.models.CatBreed mapToStorage(CatBreed domain) {
        return new ru.mirea.kartyshovav.catcatalog.data.storage.models.CatBreed(
                domain.getId(),
                domain.getName(),
                domain.getImageUrl(),
                domain.getDescription()
        );
    }

    @Override public boolean removeFromFavorites(String id) { return storage.remove(id); }
    @Override public CatBreed recognizeBreed(byte[] imageBytes) { return null; }
}
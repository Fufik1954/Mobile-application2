package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class SaveToFavoritesUseCase {
    private CatBreedRepository repository;

    public SaveToFavoritesUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public boolean execute(CatBreed catBreed) {
        if (catBreed == null) {
            return false;
        }
        return repository.saveToFavorites(catBreed);
    }
}
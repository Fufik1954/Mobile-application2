package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class RemoveFromFavoritesUseCase {
    private CatBreedRepository repository;

    public RemoveFromFavoritesUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String id) {
        return repository.removeFromFavorites(id);
    }
}
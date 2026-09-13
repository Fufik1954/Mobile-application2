package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import java.util.List;
import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class GetFavoritesUseCase {
    private CatBreedRepository repository;

    public GetFavoritesUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public List<CatBreed> execute() {
        return repository.getFavorites();
    }
}
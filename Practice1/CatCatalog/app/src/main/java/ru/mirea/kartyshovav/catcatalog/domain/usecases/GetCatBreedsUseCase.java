package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import java.util.List;
import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class GetCatBreedsUseCase {
    private CatBreedRepository repository;

    public GetCatBreedsUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public List<CatBreed> execute() {
        return repository.getCatBreeds();
    }
}
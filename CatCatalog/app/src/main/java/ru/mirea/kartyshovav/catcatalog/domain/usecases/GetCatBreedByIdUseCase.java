package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class GetCatBreedByIdUseCase {
    private CatBreedRepository repository;

    public GetCatBreedByIdUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public CatBreed execute(String id) {
        return repository.getCatBreedById(id);
    }
}
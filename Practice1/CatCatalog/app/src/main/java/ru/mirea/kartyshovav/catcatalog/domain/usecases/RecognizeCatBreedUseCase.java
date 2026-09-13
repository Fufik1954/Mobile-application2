package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class RecognizeCatBreedUseCase {
    private CatBreedRepository repository;

    public RecognizeCatBreedUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public CatBreed execute(byte[] imageBytes) {
        return repository.recognizeBreed(imageBytes);
    }
}
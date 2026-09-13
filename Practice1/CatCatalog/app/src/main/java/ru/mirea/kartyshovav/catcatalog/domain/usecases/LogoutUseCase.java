package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class LogoutUseCase {
    private CatBreedRepository repository;

    public LogoutUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.logout();
    }
}
package ru.mirea.kartyshovav.catcatalog.domain.usecases;

import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class LoginUseCase {
    private CatBreedRepository repository;

    public LoginUseCase(CatBreedRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String username, String password) {
        return repository.login(username, password);
    }
}
package ru.mirea.kartyshovav.catcatalog.domain.usecases.auth;

import ru.mirea.kartyshovav.catcatalog.domain.repository.AuthRepository;

public class LogoutUseCase {
    private final AuthRepository authRepository;

    public LogoutUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute() {
        authRepository.logout();
    }
}
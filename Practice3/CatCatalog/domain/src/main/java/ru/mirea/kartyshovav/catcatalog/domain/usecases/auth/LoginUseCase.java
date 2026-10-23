package ru.mirea.kartyshovav.catcatalog.domain.usecases.auth;

import ru.mirea.kartyshovav.catcatalog.domain.repository.AuthRepository;

public class LoginUseCase {
    private final AuthRepository authRepository;

    public LoginUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute(String email, String password, AuthRepository.AuthCallback callback) {
        authRepository.login(email, password, callback);
    }
}
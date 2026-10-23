package ru.mirea.kartyshovav.catcatalog.domain.repository;

public interface AuthRepository {

    void login(String email, String password, AuthCallback callback);
    void logout();

    // Для асинхронного ответа от Firebase
    interface AuthCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }
}
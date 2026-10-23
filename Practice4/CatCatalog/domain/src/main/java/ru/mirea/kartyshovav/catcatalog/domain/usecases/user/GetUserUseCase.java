package ru.mirea.kartyshovav.catcatalog.domain.usecases.user;

import ru.mirea.kartyshovav.catcatalog.domain.models.User;
import ru.mirea.kartyshovav.catcatalog.domain.repository.UserStorage;

public class GetUserUseCase {
    private final UserStorage userStorage;

    public GetUserUseCase(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User execute() {
        return userStorage.get();
    }
}
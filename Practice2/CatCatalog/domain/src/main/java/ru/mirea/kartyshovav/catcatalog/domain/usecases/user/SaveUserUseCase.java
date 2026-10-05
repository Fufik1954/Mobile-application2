package ru.mirea.kartyshovav.catcatalog.domain.usecases.user;

import ru.mirea.kartyshovav.catcatalog.domain.models.User;
import ru.mirea.kartyshovav.catcatalog.domain.repository.UserStorage;

public class SaveUserUseCase {
    private final UserStorage userStorage;

    public SaveUserUseCase(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean execute(User user) {
        if (user == null) return false;
        return userStorage.save(user);
    }
}
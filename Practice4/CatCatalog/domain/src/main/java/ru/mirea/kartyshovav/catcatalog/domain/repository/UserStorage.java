package ru.mirea.kartyshovav.catcatalog.domain.repository;

import ru.mirea.kartyshovav.catcatalog.domain.models.User;

public interface UserStorage {
    boolean save(User user);
    User get();
}
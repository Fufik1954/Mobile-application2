package ru.mirea.kartyshovav.catcatalog.domain.repository;

import java.util.List;
import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;

public interface CatBreedRepository {
    List<CatBreed> getCatBreeds(); // список всех пород кошек
    CatBreed getCatBreedById(String id); // порода по id

    boolean login(String username, String password); // вход в аккаунт
    void logout(); // выход
    boolean saveToFavorites(CatBreed catBreed); // сохранение в избранное
    List<CatBreed> getFavorites(); // избранные породы
    boolean removeFromFavorites(String id); // удаление из избранного
    CatBreed recognizeBreed(byte[] imageBytes); // объект ML модели
}
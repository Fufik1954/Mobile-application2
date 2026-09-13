package ru.mirea.kartyshovav.catcatalog.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;

public class CatBreedRepositoryImpl implements CatBreedRepository {

    private final List<CatBreed> testBreeds = new ArrayList<>();

    public CatBreedRepositoryImpl() {
        testBreeds.add(new CatBreed(
                "siam",
                "Siamese",
                "https://cdn2.thecatapi.com/images/siam.jpg",
                "Сиамская кошка — стройная, с голубыми глазами и тёмной мордочкой.",
                false
        ));
        testBreeds.add(new CatBreed(
                "pers",
                "Persian",
                "https://cdn2.thecatapi.com/images/pers.jpg",
                "Персидская кошка — пушистая, с плоской мордочкой и длинной шерстью.",
                false
        ));
        testBreeds.add(new CatBreed(
                "beng",
                "Bengal",
                "https://cdn2.thecatapi.com/images/beng.jpg",
                "Бенгальская кошка — с пятнами как у леопарда, очень активная.",
                false
        ));
    }

    @Override
    public List<CatBreed> getCatBreeds() {
        return testBreeds;
    }

    @Override
    public CatBreed getCatBreedById(String id) {
        for (CatBreed breed : testBreeds) {
            if (breed.getId().equals(id)) {
                return breed;
            }
        }
        return null;
    }

    @Override
    public boolean login(String username, String password) {
        // Заглушка: пока всегда успешно
        return true;
    }

    @Override
    public void logout() {
        // Заглушка: пока ничего не делаем
    }

    @Override
    public boolean saveToFavorites(CatBreed catBreed) {
        // Заглушка: пока всегда успешно
        return true;
    }

    @Override
    public List<CatBreed> getFavorites() {
        // Заглушка: возвращаем пустой список
        return new ArrayList<>();
    }

    @Override
    public boolean removeFromFavorites(String id) {
        // Заглушка: пока всегда успешно
        return true;
    }

    @Override
    public CatBreed recognizeBreed(byte[] imageBytes) {
        // Заглушка: пока ничего не распознаём
        return null;
    }
}
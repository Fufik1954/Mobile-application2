package ru.mirea.kartyshovav.catcatalog.data.storage;

import java.util.List;

import ru.mirea.kartyshovav.catcatalog.data.storage.models.CatBreed;

public interface CatBreedStorage {
    boolean save(CatBreed catBreed);
    List<CatBreed> getAll();
    boolean remove(String id);
}
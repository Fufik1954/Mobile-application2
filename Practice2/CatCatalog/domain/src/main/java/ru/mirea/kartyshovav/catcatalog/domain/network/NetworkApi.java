package ru.mirea.kartyshovav.catcatalog.domain.network;

import java.util.List;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;

public interface NetworkApi {
    List<CatBreed> getBreeds();
    CatBreed getBreedById(String id);
}
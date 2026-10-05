package ru.mirea.kartyshovav.catcatalog.data.network.mock;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.network.NetworkApi;

public class MockApi implements NetworkApi {

    @Override
    public List<CatBreed> getBreeds() {
        List<CatBreed> breeds = new ArrayList<>();

        breeds.add(new CatBreed(
                "siam",
                "Siamese",
                "https://cdn2.thecatapi.com/images/siam.jpg",
                "Сиамская кошка — стройная, с голубыми глазами и тёмной мордочкой."
        ));

        breeds.add(new CatBreed(
                "pers",
                "Persian",
                "https://cdn2.thecatapi.com/images/pers.jpg",
                "Персидская кошка — пушистая, с плоской мордочкой и длинной шерстью."
        ));

        breeds.add(new CatBreed(
                "beng",
                "Bengal",
                "https://cdn2.thecatapi.com/images/beng.jpg",
                "Бенгальская кошка — с пятнами как у леопарда, очень активная."
        ));

        return breeds;
    }

    @Override
    public CatBreed getBreedById(String id) {
        for (CatBreed breed : getBreeds()) {
            if (breed.getId().equals(id)) {
                return breed;
            }
        }
        return null;
    }
}
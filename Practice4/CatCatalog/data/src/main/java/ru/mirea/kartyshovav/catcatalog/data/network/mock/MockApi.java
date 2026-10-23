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
        breeds.add(new CatBreed(
                "main",
                "Maine Coon",
                "https://cdn2.thecatapi.com/images/main.jpg",
                "Мейн-кун — крупная порода с пушистым хвостом и кисточками на ушах."
        ));

        breeds.add(new CatBreed(
                "sphy",
                "Sphynx",
                "https://cdn2.thecatapi.com/images/sphy.jpg",
                "Сфинкс — бесшёрстная кошка с тёплой кожей и большими ушами."
        ));

        breeds.add(new CatBreed(
                "brit",
                "British Shorthair",
                "https://cdn2.thecatapi.com/images/brit.jpg",
                "Британская короткошёрстная — плюшевая шерсть и круглые глаза."
        ));

        breeds.add(new CatBreed(
                "scot",
                "Scottish Fold",
                "https://cdn2.thecatapi.com/images/scot.jpg",
                "Шотландская вислоухая — с загнутыми вперёд ушами."
        ));

        breeds.add(new CatBreed(
                "abys",
                "Abyssinian",
                "https://cdn2.thecatapi.com/images/abys.jpg",
                "Абиссинская кошка — стройная, с тикированной шерстью."
        ));

        breeds.add(new CatBreed(
                "ragd",
                "Ragdoll",
                "https://cdn2.thecatapi.com/images/ragd.jpg",
                "Рэгдолл — крупная кошка с голубыми глазами, очень ласковая."
        ));

        breeds.add(new CatBreed(
                "sibe",
                "Siberian",
                "https://cdn2.thecatapi.com/images/sibe.jpg",
                "Сибирская кошка — густая шерсть, устойчива к холоду."
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
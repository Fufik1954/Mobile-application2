package ru.mirea.kartyshovav.catcatalog;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.kartyshovav.catcatalog.domain.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.domain.repository.AuthRepository;
import ru.mirea.kartyshovav.catcatalog.domain.repository.CatBreedRepository;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.auth.LogoutUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.catbreeds.GetCatBreedByIdUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.catbreeds.GetCatBreedsUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.favorites.GetFavoritesUseCase;
import ru.mirea.kartyshovav.catcatalog.domain.usecases.favorites.SaveToFavoritesUseCase;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private final CatBreedRepository catBreedRepository;
    private final AuthRepository authRepository;

    // LiveData для одиночных результатов
    private final MutableLiveData<String> resultLiveData = new MutableLiveData<>();

    public LiveData<String> getResultLiveData() {
        return resultLiveData;
    }

    // MediatorLiveData для объединённых данных
    private final MediatorLiveData<String> combinedLiveData = new MediatorLiveData<>();

    public LiveData<String> getCombinedLiveData() {
        return combinedLiveData;
    }

    // Вспомогательные LiveData
    private final MutableLiveData<List<CatBreed>> networkBreedsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<CatBreed>> favoritesLiveData = new MutableLiveData<>();

    // LiveData для списка пород
    private final MutableLiveData<List<CatBreed>> catBreedsLiveData = new MutableLiveData<>();

    public LiveData<List<CatBreed>> getCatBreedsLiveData() {
        return catBreedsLiveData;
    }


    public MainViewModel(CatBreedRepository catBreedRepository,
                         AuthRepository authRepository) {
        this.catBreedRepository = catBreedRepository;
        this.authRepository = authRepository;
        setupMediatorLiveData();
    }

    private void setupMediatorLiveData() {
        final List<CatBreed>[] networkData = new List[]{null};
        final List<CatBreed>[] dbData = new List[]{null};

        combinedLiveData.addSource(networkBreedsLiveData, breeds -> {
            networkData[0] = breeds;
            updateCombinedResult(networkData[0], dbData[0]);
        });

        combinedLiveData.addSource(favoritesLiveData, favorites -> {
            dbData[0] = favorites;
            updateCombinedResult(networkData[0], dbData[0]);
        });
    }

    private void updateCombinedResult(List<CatBreed> network, List<CatBreed> db) {
        StringBuilder sb = new StringBuilder();
        sb.append("ДАННЫЕ ИЗ СЕТИ\n\n");
        if (network == null || network.isEmpty()) {
            sb.append("(нет данных)\n\n");
        } else {
            for (CatBreed breed : network) {
                sb.append("• ").append(breed.getName())
                        .append(" (").append(breed.getId()).append(")\n")
                        .append("  ").append(breed.getDescription()).append("\n\n");
            }
        }
        sb.append("ИЗБРАННОЕ ИЗ БД\n\n");
        if (db == null || db.isEmpty()) {
            sb.append("(избранное пусто)\n");
        } else {
            for (CatBreed breed : db) {
                sb.append("• ").append(breed.getName())
                        .append(" (").append(breed.getId()).append(")\n")
                        .append("  ").append(breed.getDescription()).append("\n\n");
            }
        }
        combinedLiveData.setValue(sb.toString());
    }

    // Метод для демонстрации MediatorLiveData
    public void loadCombinedData() {
        GetCatBreedsUseCase networkUseCase = new GetCatBreedsUseCase(catBreedRepository);
        networkBreedsLiveData.setValue(networkUseCase.execute());

        GetFavoritesUseCase dbUseCase = new GetFavoritesUseCase(catBreedRepository);
        favoritesLiveData.setValue(dbUseCase.execute());
    }

    // Одиночные методы

    public void loadCatBreeds() {
        GetCatBreedsUseCase useCase = new GetCatBreedsUseCase(catBreedRepository);
        List<CatBreed> breeds = useCase.execute();
        catBreedsLiveData.setValue(breeds);
    }

    public void loadFavorites() {
        GetFavoritesUseCase useCase = new GetFavoritesUseCase(catBreedRepository);
        List<CatBreed> favorites = useCase.execute();
        if (favorites.isEmpty()) {
            resultLiveData.setValue("Избранное пусто");
            return;
        }
        StringBuilder sb = new StringBuilder("Избранное:\n");
        for (CatBreed breed : favorites) {
            sb.append("• ").append(breed.getName())
                    .append(" (").append(breed.getId()).append(")\n")
                    .append(breed.getDescription()).append("\n\n");
        }
        resultLiveData.setValue(sb.toString());  // ← не трогаем favoritesLiveData
    }

    public void loadCatBreedById(String id) {
        GetCatBreedByIdUseCase useCase = new GetCatBreedByIdUseCase(catBreedRepository);
        CatBreed breed = useCase.execute(id);

        if (breed != null) {
            resultLiveData.setValue(
                    "Имя: " + breed.getName() + "\n" +
                            "ID: " + breed.getId() + "\n" +
                            "Описание: " + breed.getDescription() + "\n" +
                            "Ссылка на фото: " + breed.getImageUrl() + "\n"
            );
        } else {
            resultLiveData.setValue("Порода с id \"" + id + "\" не найдена");
        }
    }

    public void saveToFavorites(String id) {
        if (id.isEmpty()) {
            resultLiveData.setValue("Введите id породы");
            return;
        }

        CatBreed breed = catBreedRepository.getCatBreedById(id);
        if (breed == null) {
            resultLiveData.setValue("Порода с id \"" + id + "\" не найдена");
            return;
        }

        SaveToFavoritesUseCase useCase = new SaveToFavoritesUseCase(catBreedRepository);
        boolean result = useCase.execute(breed);

        resultLiveData.setValue("Сохранено: " + result + " (" + breed.getName() + ")");
    }

    public void logout() {
        LogoutUseCase useCase = new LogoutUseCase(authRepository);
        useCase.execute();
    }


}
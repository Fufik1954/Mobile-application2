package ru.mirea.kartyshovav.catcatalog.data.storage.room;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.mirea.kartyshovav.catcatalog.data.storage.CatBreedStorage;
import ru.mirea.kartyshovav.catcatalog.data.storage.models.CatBreed;
import ru.mirea.kartyshovav.catcatalog.data.utils.DateUtils;

public class RoomCatBreedStorage implements CatBreedStorage {

    private static final String TAG = "RoomCatBreedStorage";
    private final FavoritesDao favoritesDao;

    public RoomCatBreedStorage(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        favoritesDao = db.favoritesDao();
    }

    @Override
    public boolean save(CatBreed catBreed) {
        if (catBreed == null) return false;

        FavoritesEntity entity = new FavoritesEntity(
                catBreed.getId(),
                catBreed.getName(),
                catBreed.getImageUrl(),
                catBreed.getDescription(),
                DateUtils.nowDate()
        );

        favoritesDao.insert(entity);
        return true;
    }

    @Override
    public List<CatBreed> getAll() {
        List<FavoritesEntity> entities = favoritesDao.getAll();
        List<CatBreed> result = new ArrayList<>();

        for (FavoritesEntity entity : entities) {
            Log.d(TAG, "getAll: id=" + entity.getId()
                    + ", localDate=" + entity.getLocalDate());

            result.add(new CatBreed(
                    entity.getId(),
                    entity.getName(),
                    entity.getImageUrl(),
                    entity.getDescription()
            ));
        }
        return result;
    }

    @Override
    public boolean remove(String id) {
        favoritesDao.deleteById(id);
        return true;
    }

    private String nowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}
package ru.mirea.kartyshovav.catcatalog.data.storage.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    List<FavoritesEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoritesEntity favoritesEntity);

    @Query("DELETE FROM favorites WHERE id = :id")
    void deleteById(String id);
}
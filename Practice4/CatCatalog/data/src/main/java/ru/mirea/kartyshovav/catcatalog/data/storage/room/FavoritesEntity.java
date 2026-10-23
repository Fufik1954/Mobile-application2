package ru.mirea.kartyshovav.catcatalog.data.storage.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class FavoritesEntity {

    @PrimaryKey @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "local_date")
    private String localDate;

    public FavoritesEntity(@NonNull String id, String name, String imageUrl, String description, String localDate) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.localDate = localDate;
    }

    @NonNull
    public String getId() { return id; }
    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
    public String getLocalDate() { return localDate; }
}
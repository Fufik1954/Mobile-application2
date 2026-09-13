package ru.mirea.kartyshovav.catcatalog.domain.models;

public class CatBreed {
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private boolean isFavorite;

    public CatBreed(String id, String name, String imageUrl, String description, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
package ru.mirea.kartyshovav.catcatalog.domain.models;

public class CatBreed {
    private String id;
    private String name;
    private String imageUrl;
    private String description;

    public CatBreed(String id, String name, String imageUrl, String description) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
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
}
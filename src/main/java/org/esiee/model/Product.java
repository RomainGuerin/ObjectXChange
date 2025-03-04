package org.esiee.model;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private String description;
    private Date dateCreated;
    private String image;
    private int userId;
    private int categoryId;
    private boolean isAvailable;

    public Product(int id, String name, String description, Date dateCreated, String image, int userId, int categoryId, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.image = image;
        this.userId = userId;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
    }

    public Product(String name, String description, String image, int userId, int categoryId, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.userId = userId;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
    }

    public Product(int id, boolean isAvailable) {
        this.id = id;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

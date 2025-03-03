package org.esiee.model;

public class Item {
    private int id;
    private String name;
    private int userId;
    private int categoryId;

    public Item(int id, String name, int userId, int categoryId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Item(String name, int userId, int categoryId) {
        this.name = name;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public int getCategory() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }
}

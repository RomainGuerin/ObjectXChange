package org.esiee.model;

public class Category {
    private int id;
    private String name;

    public Category(int id, String name) {
        if (id < 0 || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid id or name");
        }
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid id, cannot be negative");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name, cannot be null or empty");
        }
        this.name = name;
    }
}

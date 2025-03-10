package org.esiee.model;

import java.util.Date;

/**
 * Represents a product with various attributes such as ID, name, description, date created, image, user ID, category ID, and availability status.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private Date dateCreated;
    private String image;
    private int userId;
    private int categoryId;
    private boolean isAvailable;

    /**
     * Constructs a new Product with the specified attributes.
     *
     * @param id the ID of the product
     * @param name the name of the product
     * @param description the description of the product
     * @param dateCreated the date the product was created
     * @param image the image of the product
     * @param userId the ID of the user who created the product
     * @param categoryId the ID of the category the product belongs to
     * @param isAvailable the availability status of the product
     */
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

    /**
     * Constructs a new Product with the specified attributes, excluding the ID and date created.
     *
     * @param name the name of the product
     * @param description the description of the product
     * @param image the image of the product
     * @param userId the ID of the user who created the product
     * @param categoryId the ID of the category the product belongs to
     * @param isAvailable the availability status of the product
     */
    public Product(String name, String description, String image, int userId, int categoryId, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.userId = userId;
        this.categoryId = categoryId;
        this.isAvailable = isAvailable;
    }

    /**
     * Returns the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id the new ID of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the new name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the product.
     *
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the new description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the date the product was created.
     *
     * @return the date the product was created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date the product was created.
     *
     * @param dateCreated the new date the product was created
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Returns the image of the product.
     *
     * @return the image of the product
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image of the product.
     *
     * @param image the new image of the product
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the ID of the user who created the product.
     *
     * @return the ID of the user who created the product
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who created the product.
     *
     * @param userId the new ID of the user who created the product
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the ID of the category the product belongs to.
     *
     * @return the ID of the category the product belongs to
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the ID of the category the product belongs to.
     *
     * @param categoryId the new ID of the category the product belongs to
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Returns the availability status of the product.
     *
     * @return true if the product is available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the product.
     *
     * @param isAvailable the new availability status of the product
     */
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
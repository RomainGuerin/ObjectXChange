package org.esiee.model;

import java.util.Date;

/**
 * Represents an exchange between two products.
 */
public class Exchange {
    private int id;
    private int productIdAsked;
    private int productIdOffered;
    private Status status;
    private Date dateCreated;
    private Date dateUpdated;

    /**
     * Constructs an Exchange with all fields.
     *
     * @param id the ID of the exchange
     * @param productIdAsked the ID of the product being asked for
     * @param productIdOffered the ID of the product being offered
     * @param status the status of the exchange
     * @param dateCreated the date the exchange was created
     * @param dateUpdated the date the exchange was last updated
     */
    public Exchange(int id, int productIdAsked, int productIdOffered, Status status, Date dateCreated, Date dateUpdated) {
        this.id = id;
        this.productIdAsked = productIdAsked;
        this.productIdOffered = productIdOffered;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    /**
     * Constructs an Exchange with required fields.
     *
     * @param productIdAsked the ID of the product being asked for
     * @param productIdOffered the ID of the product being offered
     * @param status the status of the exchange
     */
    public Exchange(int productIdAsked, int productIdOffered, Status status) {
        this.productIdAsked = productIdAsked;
        this.productIdOffered = productIdOffered;
        this.status = status;
    }

    /**
     * Gets the ID of the exchange.
     *
     * @return the ID of the exchange
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the exchange.
     *
     * @param id the new ID of the exchange
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the product being asked for.
     *
     * @return the ID of the product being asked for
     */
    public int getProductIdAsked() {
        return productIdAsked;
    }

    /**
     * Sets the ID of the product being asked for.
     *
     * @param productIdAsked the new ID of the product being asked for
     */
    public void setProductIdAsked(int productIdAsked) {
        this.productIdAsked = productIdAsked;
    }

    /**
     * Gets the ID of the product being offered.
     *
     * @return the ID of the product being offered
     */
    public int getProductIdOffered() {
        return productIdOffered;
    }

    /**
     * Sets the ID of the product being offered.
     *
     * @param productIdOffered the new ID of the product being offered
     */
    public void setProductIdOffered(int productIdOffered) {
        this.productIdOffered = productIdOffered;
    }

    /**
     * Gets the status of the exchange.
     *
     * @return the status of the exchange
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the exchange.
     *
     * @param status the new status of the exchange
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the date the exchange was created.
     *
     * @return the date the exchange was created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date the exchange was created.
     *
     * @param dateCreated the new date the exchange was created
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the date the exchange was last updated.
     *
     * @return the date the exchange was last updated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the date the exchange was last updated.
     *
     * @param dateUpdated the new date the exchange was last updated
     */
    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
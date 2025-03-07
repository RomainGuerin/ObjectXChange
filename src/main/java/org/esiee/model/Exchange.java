package org.esiee.model;

import java.util.Date;

public class Exchange {
    private int id;
    private int productIdAsked;
    private int productIdOffered;
    private Status status;
    private Date dateCreated;
    private Date dateUpdated;

    public Exchange(int id, int productIdAsked, int productIdOffered, Status status, Date dateCreated, Date dateUpdated) {
        this.id = id;
        this.productIdAsked = productIdAsked;
        this.productIdOffered = productIdOffered;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public Exchange(int productIdAsked, int productIdOffered, Status status) {
        this.productIdAsked = productIdAsked;
        this.productIdOffered = productIdOffered;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductIdAsked() {
        return productIdAsked;
    }

    public void setProductIdAsked(int productIdAsked) {
        this.productIdAsked = productIdAsked;
    }

    public int getProductIdOffered() {
        return productIdOffered;
    }

    public void setProductIdOffered(int productIdOffered) {
        this.productIdOffered = productIdOffered;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

}

package org.esiee.model;

import java.util.Date;

public class Exchange {
    public enum Status {
        Accepted,
        Pending,
        Denied
    }
    private int id;
    private int item_id_asked;
    private int item_id_offered;
    private Status status; // TODO : Enum
    private Date date_created;
    private Date date_updated;

    public Exchange(int id, int item_id_asked, int item_id_offered, Status status, Date date_created, Date date_updated) {
        this.id = id;
        this.item_id_asked = item_id_asked;
        this.item_id_offered = item_id_offered;
        this.status = status;
        this.date_created = date_created;
        this.date_updated = date_updated;
    }

    public Exchange(int item_id_asked, int item_id_offered, Status status, Date date_updated) {
        this.item_id_asked = item_id_asked;
        this.item_id_offered = item_id_offered;
        this.status = status;
        this.date_updated = date_updated;
    }

    public Exchange(int id, Status status, Date date_updated) {
        this.id = id;
        this.status = status;
        this.date_updated = date_updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id_asked() {
        return item_id_asked;
    }

    public void setItem_id_asked(int item_id_asked) {
        this.item_id_asked = item_id_asked;
    }

    public int getItem_id_offered() {
        return item_id_offered;
    }

    public void setItem_id_offered(int item_id_offered) {
        this.item_id_offered = item_id_offered;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }
}

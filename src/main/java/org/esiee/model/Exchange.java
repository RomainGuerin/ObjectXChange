package org.esiee.model;

public class Exchange {
    private int id;
    private int item_id_asked;
    private int item_id_offered;
    private String status; // TODO : Enum
    private String date_created;
    private String date_updated;

    public Exchange(int id, int item_id_asked, int item_id_offered, String status, String date_created, String date_updated) {
        this.id = id;
        this.item_id_asked = item_id_asked;
        this.item_id_offered = item_id_offered;
        this.status = status;
        this.date_created = date_created;
        this.date_updated = date_updated;
    }

    public Exchange(int item_id_asked, int item_id_offered, String status, String date_updated) {
        this.item_id_asked = item_id_asked;
        this.item_id_offered = item_id_offered;
        this.status = status;
        this.date_updated = date_updated;
    }

    public Exchange(int id, String status, String date_updated) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }
}

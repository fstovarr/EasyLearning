package com.suativa.easylearning.model;

public class ListenImitateItem {
    private String id;
    private String title;
    private int imageId;

    public ListenImitateItem() {
    }

    public ListenImitateItem(String id, String title, int imageId) {
        this.id = id;
        this.title = title;
        this.imageId = imageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

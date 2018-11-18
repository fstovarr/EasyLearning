package com.suativa.easylearning.model;

public class Game {
    private String title;
    private String littleDescription;
    private String id;
    private int difficulty;
    private int image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLittleDescription() {
        return littleDescription;
    }

    public void setLittleDescription(String littleDescription) {
        this.littleDescription = littleDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

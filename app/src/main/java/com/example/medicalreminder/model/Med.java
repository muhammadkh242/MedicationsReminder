package com.example.medicalreminder.model;

public class Med {
    private String name;
    private int strength;
    private String pill;
    private int thumbnail;

    public Med(String name, int strength, String pill, int thumbnail) {
        this.name = name;
        this.strength = strength;
        this.pill = pill;
        this.thumbnail = thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setPill(String pill) {
        this.pill = pill;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public String getPill() {
        return pill;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}

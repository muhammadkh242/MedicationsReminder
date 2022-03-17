package com.example.medicalreminder.model;


import java.io.Serializable;

public class UserMed implements Serializable {
    String name;
    String form;

    public UserMed() {
    }

    public UserMed(String name, String form) {
        this.name = name;
        this.form = form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }
}

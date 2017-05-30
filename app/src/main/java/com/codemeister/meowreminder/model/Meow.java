package com.codemeister.meowreminder.model;

/**
 * Created by imgur on 27/05/2017.
 */

public class Meow {
    private int id;
    private String catname;
    private String breed;

    public Meow() {
    }

    public Meow(int id, String catname, String breed) {
        this.id = id;
        this.catname = catname;
        this.breed = breed;
    }

    public Meow(String catname, String breed) {
        this.catname = catname;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}

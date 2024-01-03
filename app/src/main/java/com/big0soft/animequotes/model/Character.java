package com.big0soft.animequotes.model;

import java.util.List;

public class Character {
    private int characterId;
    private String name;
    private String image;
    private List<Quote> quotes;


    public Character() {

    }

    public Character(String name) {
        this.name = name;
    }

    public Character(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Character(String name, String image, List<Quote> quotes) {
        this.name = name;
        this.image = image;
        this.quotes = quotes;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "Character{" +
                "characterId=" + characterId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", quotes=" + quotes +
                '}';
    }
}

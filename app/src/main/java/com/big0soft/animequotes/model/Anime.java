package com.big0soft.animequotes.model;

import java.util.List;

public class Anime {
    private int animeId;
    private String name;
    private String image;
    private String description;
    private List<Character> characters;


    public Anime() {

    }

    public Anime(String name) {
        this.name = name;
    }

    public Anime(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Anime(String name, String image, List<Character> characters) {
        this.name = name;
        this.image = image;
        this.characters = characters;
    }

    public Anime(String name, String image, String description, List<Character> characters) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.characters = characters;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "animeId=" + animeId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", characters=" + characters +
                '}';
    }
}

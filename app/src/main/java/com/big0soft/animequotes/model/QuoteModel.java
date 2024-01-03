package com.big0soft.animequotes.model;

public class QuoteModel {
    private String image ;
    private String quote ;
    private String character;
    private String animeName ;
    public QuoteModel() {
    }

    public QuoteModel(String image, String quote, String character, String animeName) {
        this.image = image;
        this.quote = quote;
        this.character = character;
        this.animeName = animeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }
}

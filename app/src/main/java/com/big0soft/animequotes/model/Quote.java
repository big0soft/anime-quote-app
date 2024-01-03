package com.big0soft.animequotes.model;

public class Quote {
    private int quoteId;
    private String quote;
    private Anime anime;
    private Character character;


    public Quote() {

    }

    public Quote(String quote) {
        this.quote = quote;
    }

    public Quote(String quote, Anime anime, Character character) {
        this.quote = quote;
        this.anime = anime;
        this.character = character;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteId=" + quoteId +
                ", quote='" + quote + '\'' +
                ", anime=" + anime +
                ", character=" + character +
                '}';
    }
}

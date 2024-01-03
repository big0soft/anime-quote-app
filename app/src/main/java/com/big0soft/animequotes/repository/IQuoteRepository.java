package com.big0soft.animequotes.repository;

import com.big0soft.animequotes.model.Anime;
import com.big0soft.animequotes.model.Character;
import com.big0soft.animequotes.model.Quote;

import java.util.List;

import io.reactivex.Single;

public interface IQuoteRepository {

    /**
     * @return all quotes
     */
    Single<List<Quote>> getQuotes();

    /**
     * @param character the character say the quote
     * @return the quotes of the character
     */
    List<Quote> getAnimeQuote(Character character);

    /**
     * @return the quotes of the anime
     */
    List<Quote> getAnimeQuote(Anime anime);

    List<Quote> getCharacterQuotes(Character character);


    List<Quote> searchQuote(String quote);




}

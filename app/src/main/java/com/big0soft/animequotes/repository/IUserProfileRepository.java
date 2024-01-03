package com.big0soft.animequotes.repository;

import com.big0soft.animequotes.model.Anime;
import com.big0soft.animequotes.model.Quote;

import java.util.List;

public interface IUserProfileRepository {

    List<Anime> getUserAnimes();
    List<Character> getUserCharacters();
    List<Quote> getSaveQuotes(String orderBy);
    List<Quote> getSharedQuotes(String orderBy);
}

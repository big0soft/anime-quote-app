package com.big0soft.animequotes.repository;

import com.big0soft.animequotes.model.Anime;
import com.big0soft.animequotes.model.Quote;
import com.big0soft.animequotes.model.Character;

import java.util.List;

import io.reactivex.Single;

public interface IAnimeRepository {

    /**
     * @return all {@link Anime} quotes
     */
    Single<List<Anime>> getAnimes();

    /**
     * @return all {@link Anime} quote like the anime name
     */
    Single<List<Anime>> searchAnime(String anime);




    /**
     * @return the {@link Quote} and {@link Character} of the anime
     */
    Single<Anime> getAnime(String anime);
}

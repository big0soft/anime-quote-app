package com.big0soft.animequotes.repository;

import com.big0soft.animequotes.model.Anime;
import com.big0soft.animequotes.model.Character;

import java.util.List;

import io.reactivex.Single;

public interface ICharacterRepository {

    /**
     * @return all {@link Character}
     */
    Single<List<Character>> getCharacters();

    /**
     * @param anime the search {@link Anime}
     * @return list of anime {@link Character}
     */
    Single<List<Character>> getAnimeCharacters(Anime anime);

    /**
     * @return get info about {@link Character}
     */
    Single<Character> getCharacter(String name);

    /**
     * @return list of anime {@link Character}
     */
    Single<List<Character>> searchCharacter(String search);

}

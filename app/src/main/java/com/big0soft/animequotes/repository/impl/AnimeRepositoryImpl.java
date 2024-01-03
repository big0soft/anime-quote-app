package com.big0soft.animequotes.repository.impl;

import com.big0soft.animequotes.model.Anime;
import com.big0soft.animequotes.repository.BaseRepository;
import com.big0soft.animequotes.repository.IAnimeRepository;

import java.util.List;

import io.reactivex.Single;

public class AnimeRepositoryImpl extends BaseRepository implements IAnimeRepository {



    @Override
    public Single<List<Anime>> getAnimes() {
        return getApiService().getAllAnime();
    }

    @Override
    public Single<List<Anime>> searchAnime(String anime) {
        return null;
    }

    @Override
    public Single<Anime> getAnime(String anime) {
        return null;
    }
}

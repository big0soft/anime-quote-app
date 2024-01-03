package com.big0soft.animequotes.service;

import com.big0soft.animequotes.model.Anime;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("anime/all")
    Single<List<Anime>> getAllAnime();
}

package com.big0soft.animequotes.repository;

import com.big0soft.animequotes.rest.RetrofitApiConfig;
import com.big0soft.animequotes.service.ApiService;

public class BaseRepository {
    private final ApiService apiService;

    public BaseRepository() {
        this(RetrofitApiConfig.getInstance().createService());
    }

    public BaseRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public ApiService getApiService() {
        return apiService;
    }
}

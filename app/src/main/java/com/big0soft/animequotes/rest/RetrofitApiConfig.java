package com.big0soft.animequotes.rest;

import com.big0soft.animequotes.service.ApiService;
import com.big0soft.resource.BuildConfig;
import com.big0soft.resource.retrofit.RetrofitClientImpl;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public final class RetrofitApiConfig extends RetrofitClientImpl {

    private final Retrofit retrofit;
    private static RetrofitApiConfig retrofitApiConfig;

    private RetrofitApiConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addCallAdapterFactory(adapterFactory().adapterFactory())
                .addConverterFactory(converterFactory().converterFactory())
                .client(HttpLogHelper.logHttpRequest(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static RetrofitApiConfig getInstance() {
        if (retrofitApiConfig == null) {
            synchronized (RetrofitApiConfig.class){
                retrofitApiConfig = new RetrofitApiConfig();
            }
        }
        return retrofitApiConfig;
    }


    private String getBaseUrl() {
        if (BuildConfig.DEBUG) {
            return "http://192.168.1.16:9091/";
        }
        return "https://ahmad-dev.com/animequotes-v1/api/";
    }

    public ApiService createService() {
        return retrofit.create(ApiService.class);
    }
}

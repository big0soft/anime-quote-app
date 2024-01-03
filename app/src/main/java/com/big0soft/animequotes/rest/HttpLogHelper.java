package com.big0soft.animequotes.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogHelper {

    public static OkHttpClient logHttpRequest(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(level);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        return httpClient.build();
    }
}

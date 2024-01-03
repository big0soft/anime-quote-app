package com.big0soft.resource.retrofit;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConverterFactoryImpl implements IConverterFactory {
    @Nullable
    private Gson gson;

    @Override
    public Converter.Factory converterFactory() {
        if (gson == null) {
            return GsonConverterFactory.create();
        }
        return GsonConverterFactory.create(gson);
    }
}

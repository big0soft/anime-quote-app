package com.big0soft.resource.retrofit;

import retrofit2.CallAdapter;
import retrofit2.Converter;

public interface IConverterFactory {
    Converter.Factory converterFactory();
}

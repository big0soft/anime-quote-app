package com.big0soft.resource.retrofit;

public class RetrofitClientImpl extends BaseRetrofitClient {
    @Override
    public IAdapterFactory adapterFactory() {
        return new AdapterFactoryImpl();
    }

    @Override
    public IConverterFactory converterFactory() {
        return new ConverterFactoryImpl();
    }
}

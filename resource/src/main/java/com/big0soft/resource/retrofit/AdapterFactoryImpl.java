package com.big0soft.resource.retrofit;

import retrofit2.CallAdapter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class AdapterFactoryImpl implements IAdapterFactory {
    @Override
    public CallAdapter.Factory adapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}

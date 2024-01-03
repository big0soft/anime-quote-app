package com.big0soft.resource.retrofit;

import com.big0soft.resource.encryption.CustomKeyEncoder;
import com.big0soft.resource.http.DecodedURLImpl;
import com.big0soft.resource.http.IDecodedURL;

public class RetrofitClientEncodedUrlImpl extends RetrofitClientEncodedUrl {


    private final CustomKeyEncoder customKeyEncoder;

    public RetrofitClientEncodedUrlImpl(CustomKeyEncoder customKeyEncoder) {
        this.customKeyEncoder = customKeyEncoder;
    }

    @Override
    public IDecodedURL decodedURL() {
        return new DecodedURLImpl(customKeyEncoder);
    }
}

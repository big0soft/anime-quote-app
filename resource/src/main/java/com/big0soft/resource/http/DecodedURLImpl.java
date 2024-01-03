package com.big0soft.resource.http;

import com.big0soft.resource.encryption.CustomKeyEncoder;

public class DecodedURLImpl implements IDecodedURL {

    private final CustomKeyEncoder customKeyEncoder;

    public DecodedURLImpl(CustomKeyEncoder customKeyEncoder) {
        this.customKeyEncoder = customKeyEncoder;
    }

    @Override
    public String decode(String url) {
        return customKeyEncoder.decode(url);
    }
}

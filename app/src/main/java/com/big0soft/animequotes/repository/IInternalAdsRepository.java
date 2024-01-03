package com.big0soft.animequotes.repository;

import com.big0soft.animequotes.model.InternalAds;

import java.util.List;

import io.reactivex.Single;

public interface IInternalAdsRepository {

    Single<List<InternalAds>> getInternalAppAds();

}

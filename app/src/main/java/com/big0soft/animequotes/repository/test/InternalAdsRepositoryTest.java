package com.big0soft.animequotes.repository.test;

import com.big0soft.animequotes.model.InternalAds;
import com.big0soft.animequotes.repository.IInternalAdsRepository;

import java.util.List;

import io.reactivex.Single;

public class InternalAdsRepositoryTest implements IInternalAdsRepository {
    @Override
    public Single<List<InternalAds>> getInternalAppAds() {
        return Single.just(List.of(
                new InternalAds("https://www.lucidadvertising.com/wp-content/uploads/2021/09/diff_digi_ads.jpg"),
                new InternalAds("https://martechseries.com/wp-content/uploads/2023/05/Digital-Ad-Spend-Trends-1.jpg"),
                new InternalAds("https://w3vitals.com/static/e376d218c72b31199f91523c8a414401/e3121/Google_Ads.jpg"),
                new InternalAds("https://www.exchangewire.com/wp-content/uploads/2023/03/13450330_OrgCoral_Ofc-01_Concept-06-scaled.jpg"),
                new InternalAds("https://www.socialmediaexaminer.com/wp-content/uploads/2023/05/meta-messaging-ads-instagram-facebook-how-to-get-quality-leads-1600.png")
        ));
    }
}

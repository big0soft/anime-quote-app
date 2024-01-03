package com.big0soft.animequotes.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.animequotes.model.InternalAds;
import com.big0soft.animequotes.model.Quote;
import com.big0soft.animequotes.repository.IInternalAdsRepository;
import com.big0soft.animequotes.repository.IQuoteRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private IQuoteRepository quoteRepository;
    private IInternalAdsRepository internalAdsRepository;

    private final MutableLiveData<List<Quote>> quotesLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<InternalAds>> internalAdsLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public HomeViewModel(IQuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public HomeViewModel(IQuoteRepository quoteRepository, IInternalAdsRepository internalAdsRepository) {
        this.quoteRepository = quoteRepository;
        this.internalAdsRepository = internalAdsRepository;
    }
    //    public HomeViewModel() {
//
//    }

    public void getMostPopularQuotes() {
        compositeDisposable.add(quoteRepository.getQuotes()
                .subscribeOn(Schedulers.io())
                .subscribe(quotesLiveData::postValue, Throwable::printStackTrace));

    }


    public void getInternalAds() {
        compositeDisposable.add(internalAdsRepository.getInternalAppAds()
                .subscribeOn(Schedulers.io())
                .subscribe(internalAdsLiveData::postValue, Throwable::printStackTrace));
    }

    public LiveData<List<InternalAds>> getInternalAdsLiveData() {
        return internalAdsLiveData;
    }

    public LiveData<List<Quote>> getQuotesLiveData() {
        return quotesLiveData;
    }

    public static class HomeViewModelFactory implements ViewModelProvider.Factory {

        private IQuoteRepository quoteRepository;
        private IInternalAdsRepository internalAdsRepository;

        public HomeViewModelFactory(IQuoteRepository quoteRepository, IInternalAdsRepository internalAdsRepository) {
            this.quoteRepository = quoteRepository;
            this.internalAdsRepository = internalAdsRepository;
        }

        public HomeViewModelFactory(IQuoteRepository quoteRepository) {
            this.quoteRepository = quoteRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

            return (T) new HomeViewModel(quoteRepository, internalAdsRepository);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}

package com.big0soft.animequotes.viewmodel;

import static io.reactivex.schedulers.Schedulers.io;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.animequotes.R;
import com.big0soft.animequotes.model.Anime;
import com.big0soft.animequotes.repository.IAnimeRepository;
import com.big0soft.animequotes.repository.impl.AnimeRepositoryImpl;
import com.big0soft.resource.model.UIError;
import com.big0soft.resource.response.DataResponse;
import com.big0soft.resource.utils.RxJavaUtils;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class AnimeCategoryViewModel extends ViewModel {

    private final IAnimeRepository animeRepository;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Anime>> animeLiveDataResult = new MutableLiveData<>();
    private final MutableLiveData<DataResponse<?>> animeLiveDataError = new MutableLiveData<>();
    private final MutableLiveData<UIError> animeLiveDataUIError = new MutableLiveData<>();

    public AnimeCategoryViewModel(IAnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }


    public void loadAnimeCategory() {
        compositeDisposable.add(animeRepository.getAnimes()
                .subscribeOn(io())
                .subscribe(value -> {
                            if (value.isEmpty()) {
                                animeLiveDataUIError.postValue(new UIError(
                                        true, R.mipmap.ic_launcher_logo_round, "Error"
                                        , "No data found"
                                ));
                                return;
                            }
                            animeLiveDataResult.postValue(value);
                        }
                        , RxJavaUtils.handleHttpException(animeLiveDataError)));
    }

    public LiveData<List<Anime>> getAnimeLiveDataResult() {
        return animeLiveDataResult;
    }

    public LiveData<UIError> getAnimeLiveDataUIError() {
        return animeLiveDataUIError;
    }

    public LiveData<DataResponse<?>> getAnimeLiveDataError() {
        return animeLiveDataError;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public static class AnimeCategoryViewModelFactory implements ViewModelProvider.Factory {
        private final IAnimeRepository animeRepository;

        public AnimeCategoryViewModelFactory() {
            this(new AnimeRepositoryImpl());
        }

        public AnimeCategoryViewModelFactory(IAnimeRepository animeRepository) {
            this.animeRepository = animeRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new AnimeCategoryViewModel(animeRepository);
        }
    }
}

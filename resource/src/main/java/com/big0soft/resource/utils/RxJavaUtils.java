package com.big0soft.resource.utils;

import static com.big0soft.resource.utils.StrUtils.isEmpty;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.big0soft.resource.BuildConfig;
import com.big0soft.resource.helper.TAGs;
import com.big0soft.resource.response.DataResponse;
import com.big0soft.resource.retrofit.handle.HandleErrorResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtils {
    public static Disposable observableInterval(Consumer<Long> subscribe) {
        long interval = BuildConfig.DEBUG ? 1L : 10L;
        return observableInterval(interval, TimeUnit.SECONDS, subscribe);

    }

    public static boolean isValid(String json) {
        if (isEmpty(json)) {
            return false;
        }
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static Consumer<Throwable> handleHttpException(MutableLiveData<DataResponse<?>> liveData) {
        return throwable -> {
            DataResponse<?> dataResponse = HandleErrorResponseBody.responseErrorBody(throwable);
            liveData.postValue(dataResponse);
        };

    }


    public static Disposable observableInterval(long period, TimeUnit unit, Consumer<Long> subscribe) {
        return Observable.interval(period, unit, Schedulers.io())
                .distinctUntilChanged()
                .subscribe(subscribe, throwable -> {
                    Log.e(TAGs.TAG, "observableInterval: ", throwable);
                });

    }

    /**
     * Creates a countdown timer that emits values from the specified duration down to 0.
     *
     * @param durationInSeconds The duration of the countdown in seconds.
     * @return An Observable that emits the countdown values.
     */
    public static Observable<Integer> createCountdown(int durationInSeconds) {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(tick -> durationInSeconds - tick.intValue())
                .take(durationInSeconds + 1)
                .flatMap(remaining -> remaining == 0 ? Single.just(remaining).toObservable() : Observable.just(remaining));
    }
}

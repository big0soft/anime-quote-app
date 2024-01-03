package com.big0soft.resource.retrofit.handle;


import com.big0soft.resource.http.HttpStatusUtils;
import com.big0soft.resource.response.DataResponse;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public final class HandleErrorResponseBody {

    public static DataResponse<?> responseErrorBody(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = ((HttpException) throwable);
            retrofit2.Response<?> response = httpException.response();
            DataResponse<?> errorResponse = new DataResponse<>(true);
            if (response == null) {
                return errorResponse;
            }
            errorResponse.setResponseCode(response.code());
            errorResponse.setMessage(HttpStatusUtils.getMessageForStatusCode(response.code()));
            ResponseBody responseBody = response.errorBody();
            if (responseBody == null) {
                return errorResponse;
            }
            try {
                errorResponse = (DataResponse<?>) errorResponse.fromJson(responseBody.string());
                errorResponse.setError(true);
            } catch (Exception ignored) {
            }
            return errorResponse;
        }
        return new DataResponse<>("Unknown error", true);
    }


}

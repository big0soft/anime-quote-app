package com.big0soft.resource.response;


import com.big0soft.resource.data.FromJson;
import com.big0soft.resource.data.ToJson;
import com.big0soft.resource.gson.FromJsonImpl;
import com.big0soft.resource.gson.ToJsonImpl;
import com.google.gson.annotations.SerializedName;

public class DataResponse<T> implements FromJson, ToJson {



    private String message;
    @SerializedName("state_code")
    private int responseCode;
    private boolean error;
    private T data;

    public DataResponse() {

    }

    public DataResponse(boolean error) {
        this.error = error;
    }

    public DataResponse(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    public DataResponse(String message, boolean error, int responseCode) {
        this.message = message;
        this.responseCode = responseCode;
        this.error = error;
    }

    public DataResponse(T data) {
        this.data = data;
    }

    public DataResponse(String message) {
        this.message = message;
        error = true;
    }

    public String message() {
        return message;
    }

    public DataResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public int responseCode() {
        return responseCode;
    }

    public DataResponse<T> setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public boolean error() {
        return error;
    }

    public DataResponse<T> setError(boolean error) {
        this.error = error;
        return this;
    }

    public T data() {
        return data;
    }

    public DataResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public FromJson fromJson(String data) {
        return new FromJsonImpl<>(getClass());
    }

    @Override
    public String toJson() {
        return new ToJsonImpl<>(this).toJson();
    }
}

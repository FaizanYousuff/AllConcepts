package com.example.faizan.allconcepts.api;

public interface GetResponseListner<TYPE> {

    void success(TYPE ret);
    void onError(String errorData);
}

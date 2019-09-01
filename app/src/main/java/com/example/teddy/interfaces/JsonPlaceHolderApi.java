package com.example.teddy.interfaces;

import com.example.teddy.model.Features;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET
    Call<Features> getFeatures(@Url String url);
}

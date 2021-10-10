package com.testdemo.acronymstest.model.api;

import com.testdemo.acronymstest.model.modelclass.AcronymList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @GET("dictionary.py")
    Call<List<AcronymList>> getAcronymsData(@Query("sf") String search);


}
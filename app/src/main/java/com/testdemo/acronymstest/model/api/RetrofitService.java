package com.testdemo.acronymstest.model.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "http://www.nactem.ac.uk/software/acromine/";
    private static RetrofitService myClient;
    private Retrofit retrofit;

    private RetrofitService() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(setLogging()).build();
    }

    public static synchronized RetrofitService getInstance() {
        if (myClient == null) {
            myClient = new RetrofitService();
        }
        return myClient;
    }

    public ApiClient getMyApi() {
        return retrofit.create(ApiClient.class);
    }
    public static OkHttpClient setLogging(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return okHttpClient;
    }
}
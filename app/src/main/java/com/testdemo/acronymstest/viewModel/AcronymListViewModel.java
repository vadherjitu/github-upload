package com.testdemo.acronymstest.viewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.testdemo.acronymstest.model.api.ApiClient;
import com.testdemo.acronymstest.model.api.RetrofitService;
import com.testdemo.acronymstest.model.modelclass.AcronymList;
import com.testdemo.acronymstest.model.modelclass.FullForm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcronymListViewModel extends ViewModel {
    public MutableLiveData<Boolean> isShowProgress = new MutableLiveData<>();

    /*for observe and expose failure or eny expection to main view*/
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    /*for observe and expose list of data to main view*/
    public MutableLiveData<AcronymList> mutableLiveData = new MutableLiveData<>();

    /* constructor for intialize viewmodel*/
    public AcronymListViewModel() {
    }

    public MutableLiveData<AcronymList> getMutableLiveData(String inputWord) {
        isShowProgress.postValue(true);

        /* Retrofit for fetch data from api  quick as possible*/
        ApiClient api = RetrofitService.getInstance().getMyApi();
        Call<List<AcronymList>> call = api.getAcronymsData(inputWord);
        call.enqueue(new Callback<List<AcronymList>>() {
            @Override
            public void onResponse(Call<List<AcronymList>> call, Response<List<AcronymList>> response) {
                isShowProgress.postValue(false);
                if (response.body() != null && !response.body().isEmpty()) {
                    /*Post data to view which one is requested*/
                    mutableLiveData.postValue(response.body().get(0));
                } else {
                    errorMessage.postValue("No data found for enter acronym");
                }
            }

            @Override
            public void onFailure(Call<List<AcronymList>> call, Throwable t) {
                isShowProgress.postValue(false);
                errorMessage.postValue(t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
package com.testdemo.acronymstest.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.testdemo.acronymstest.databinding.AcronymsActivityBinding;
import com.testdemo.acronymstest.model.modelclass.AcronymList;
import com.testdemo.acronymstest.utils.Util;
import com.testdemo.acronymstest.view.adapters.MyAcronymAdapter;
import com.testdemo.acronymstest.viewModel.AcronymListViewModel;

public class AcronymsActivity extends AppCompatActivity {

    AcronymsActivityBinding mAcronymsActivityBinding;//View Binding
    private AcronymListViewModel acronymListViewModel;//VIEWMODEL for get data
    private MyAcronymAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Here i have used viewbinding for get rid of findviewbyid */
        mAcronymsActivityBinding = AcronymsActivityBinding.inflate(getLayoutInflater());
        setContentView(mAcronymsActivityBinding.getRoot());

        /* Use of ViewModel part of MVVM*/
        acronymListViewModel = ViewModelProviders.of(this).get(AcronymListViewModel.class);

        mAcronymsActivityBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mAcronymsActivityBinding.editTextAncronym.getText().toString().isEmpty()) {
                    /*USing Mutable Livedata here we can get changes from viewmodel class where viewmodel have expose the data of observer*/
                    acronymListViewModel.getMutableLiveData(mAcronymsActivityBinding.editTextAncronym.getText().toString().trim()).observe(AcronymsActivity.this, new Observer<AcronymList>() {
                        @Override
                        public void onChanged(AcronymList mAcronymListArray) {
                            mAcronymsActivityBinding.rlvAcronym.setVisibility(View.VISIBLE);
                            adapter = new MyAcronymAdapter(mAcronymListArray.getfullformList(), AcronymsActivity.this);
                            mAcronymsActivityBinding.rlvAcronym.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            mAcronymsActivityBinding.rlvAcronym.setAdapter(adapter);
                            Util.hideKeyboard(AcronymsActivity.this);
                        }
                    });
                } else {
                    Toast.makeText(AcronymsActivity.this, "Enter something to find", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*While fetched any error its comes here after observable observed the data ane expose here */
        acronymListViewModel.errorMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String failMessage) {
                mAcronymsActivityBinding.editTextAncronym.setText("");
                mAcronymsActivityBinding.rlvAcronym.setVisibility(View.GONE);
                Toast.makeText(AcronymsActivity.this, failMessage, Toast.LENGTH_SHORT).show();
            }
        });
        /*For show hide progress dialog */
        acronymListViewModel.isShowProgress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isShow) {
                if (isShow) {
                    Util.hideKeyboard(AcronymsActivity.this);
                    mAcronymsActivityBinding.progressBar.setVisibility(View.VISIBLE);
                } else {
                    mAcronymsActivityBinding.progressBar.setVisibility(View.GONE);
                }
            }
        });
        /*When edittext clear dont need to show list */
        mAcronymsActivityBinding.editTextAncronym.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    mAcronymsActivityBinding.rlvAcronym.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(AcronymsActivity.this, "Hope you like this come back if anything you want to find for acronym", Toast.LENGTH_LONG).show();

        super.onBackPressed();
    }
}
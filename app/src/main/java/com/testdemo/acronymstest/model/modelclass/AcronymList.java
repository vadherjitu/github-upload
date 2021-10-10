package com.testdemo.acronymstest.model.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AcronymList {

    @SerializedName("sf")
    @Expose
    private String sf;
    @SerializedName("lfs")
    @Expose
    private List<FullForm> fullformList = null;

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public List<FullForm> getfullformList() {
        return fullformList;
    }

    public void setfullformList(List<FullForm> lfs) {
        this.fullformList = lfs;
    }


}
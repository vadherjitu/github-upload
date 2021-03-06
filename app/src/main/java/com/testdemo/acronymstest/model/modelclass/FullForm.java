package com.testdemo.acronymstest.model.modelclass;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.testdemo.acronymstest.utils.Util;

public class FullForm {

    @SerializedName("lf")
    private String lf;
    @Expose
    private Integer freq;
    @SerializedName("since")
    @Expose
    private Integer since;
    @SerializedName("vars")
    @Expose
    private List<Var> vars = null;


    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public Integer getSince() {
        return since;
    }

    public void setSince(Integer since) {
        this.since = since;
    }

    public List<Var> getVars() {
        return vars;
    }

    public void setVars(List<Var> vars) {
        this.vars = vars;
    }

    public String getLf() {
        return  Util.makeWordsCaptilize(lf);
    }

    public void setLf(String lf) {
        this.lf = lf;
    }


}

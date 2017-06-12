package com.josalvdel1.randomusercodetest.data.api.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResults {

    @SerializedName("results")
    @Expose
    private List<ApiUser> results;

    public List<ApiUser> getResults() {
        return results;
    }
}

package com.josalvdel1.randomusercodetest.data.api;


import com.josalvdel1.randomusercodetest.data.api.entity.ApiResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApiService {

    String USER_PATH = "/";

    @GET(USER_PATH)
    Call<ApiResults> getUsers(@Query("results") int count);
}
package com.josalvdel1.randomusercodetest.api;


import com.josalvdel1.randomusercodetest.api.entity.ApiResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApiService {

    String USER_PATH = "/";

    @GET(USER_PATH)
    Call<ApiResults> getUsers(@Query("results") int count);
}
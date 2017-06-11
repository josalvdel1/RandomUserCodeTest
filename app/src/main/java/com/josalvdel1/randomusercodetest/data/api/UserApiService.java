package com.josalvdel1.randomusercodetest.data.api;


import com.josalvdel1.randomusercodetest.data.api.entity.ApiUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApiService {

    String USER_PATH = "/";

    @GET(USER_PATH)
    Call<List<ApiUser>> getUsers(@Query("results") int count);
}
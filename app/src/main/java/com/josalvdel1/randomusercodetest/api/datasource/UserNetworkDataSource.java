package com.josalvdel1.randomusercodetest.api.datasource;

import com.josalvdel1.randomusercodetest.api.UserApiService;
import com.josalvdel1.randomusercodetest.api.entity.ApiResults;
import com.josalvdel1.randomusercodetest.api.entity.ApiUser;
import com.josalvdel1.randomusercodetest.api.exception.NetworkException;
import com.josalvdel1.randomusercodetest.api.mapper.UserMapper;
import com.josalvdel1.randomusercodetest.api.mapper.base.ListMapper;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class UserNetworkDataSource {

    private final UserApiService userApiService;

    private final UserMapper userMapper;

    @Inject
    public UserNetworkDataSource(UserApiService userApiService, UserMapper userMapper) {
        this.userApiService = userApiService;
        this.userMapper = userMapper;
    }

    public List<User> fetchUsers(int itemCount) throws Exception {
        Response<ApiResults> response = userApiService.getUsers(itemCount).execute();

        if (response.isSuccessful()) {
            ListMapper<User, ApiUser> userListMapper = new ListMapper<>(userMapper);
            return userListMapper.apiToModel(response.body().getResults());
        } else {
            throw new NetworkException();
        }
    }
}

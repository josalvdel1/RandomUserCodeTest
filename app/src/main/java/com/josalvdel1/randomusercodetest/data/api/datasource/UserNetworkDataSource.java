package com.josalvdel1.randomusercodetest.data.api.datasource;

import com.josalvdel1.randomusercodetest.data.api.UserApiService;
import com.josalvdel1.randomusercodetest.data.api.entity.ApiUser;
import com.josalvdel1.randomusercodetest.data.api.exception.NetworkException;
import com.josalvdel1.randomusercodetest.data.api.mapper.UserMapper;
import com.josalvdel1.randomusercodetest.data.api.mapper.base.ListMapper;
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

    public List<User> getUsers(int count) throws Exception {
        Response<List<ApiUser>> response = userApiService.getUsers(count).execute();

        if (response.isSuccessful()) {
            ListMapper<User, ApiUser> userListMapper = new ListMapper<>(userMapper);
            return userListMapper.apiToModel(response.body());
        } else {
            throw new NetworkException();
        }
    }
}

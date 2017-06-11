package com.josalvdel1.randomusercodetest.domain.repository;

import com.josalvdel1.randomusercodetest.data.api.datasource.UserNetworkDataSource;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

import javax.inject.Inject;

public class UserRepository {

    private UserNetworkDataSource userNetworkDataSource;

    @Inject
    public UserRepository(UserNetworkDataSource userNetworkDataSource) {
        this.userNetworkDataSource = userNetworkDataSource;
    }

    public List<User> getUsers(int count) throws Exception {
        return userNetworkDataSource.getUsers(count);
    }
}

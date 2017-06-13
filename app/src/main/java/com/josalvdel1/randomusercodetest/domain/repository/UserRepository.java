package com.josalvdel1.randomusercodetest.domain.repository;

import com.josalvdel1.randomusercodetest.data.api.datasource.UserNetworkDataSource;
import com.josalvdel1.randomusercodetest.domain.datasource.UserDbDataSource;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

import javax.inject.Inject;

public class UserRepository {

    private UserNetworkDataSource userNetworkDataSource;
    private UserDbDataSource userDbDataSource;

    @Inject
    public UserRepository(UserNetworkDataSource userNetworkDataSource, UserDbDataSource userDbDataSource) {
        this.userNetworkDataSource = userNetworkDataSource;
        this.userDbDataSource = userDbDataSource;
    }

    public List<User> fetchMoreUsers(int count) throws Exception {
        List<User> users = userNetworkDataSource.fetchUsers(count);
        if (users != null && users.size() > 0) {
            userDbDataSource.storeUsers(users);
        }
        return userDbDataSource.getAllUsers();
    }

    public List<User> getOldUsers() {
        return userDbDataSource.getAllUsers();
    }
}

package com.josalvdel1.randomusercodetest.domain.datasource;

import android.support.annotation.NonNull;

import com.josalvdel1.randomusercodetest.domain.database.UserDao;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDbDataSource {

    private UserDao userDao;

    @Inject
    public UserDbDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void storeUsers(List<User> users) {
        userDao.insertAll(users);
    }

    public void addToBlackList(@NonNull User user) {
        user.setBlackListed(true);
        userDao.updateUser(user);
    }
}

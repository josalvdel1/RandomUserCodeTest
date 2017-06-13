package com.josalvdel1.randomusercodetest.presentation.ui.module.users;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.josalvdel1.randomusercodetest.MyApplication;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {

    MutableLiveData<List<User>> userLiveData;

    public UserListViewModel(Application application) {
        super(application);
        ((MyApplication) application).getAppComponent().inject(this);
    }

    public LiveData<List<User>> bindDataOrigin() {
        if (userLiveData == null) {
            userLiveData = new MutableLiveData<>();
        }
        return userLiveData;
    }

    public void updateUserList(List<User> users) {
        userLiveData.setValue(users);
    }
}

package com.josalvdel1.randomusercodetest.presentation.ui.module.userdetail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.josalvdel1.randomusercodetest.MyApplication;
import com.josalvdel1.randomusercodetest.domain.entity.User;

public class UserDetailViewModel extends AndroidViewModel {

    private MutableLiveData<User> userLiveData;

    public UserDetailViewModel(Application application) {
        super(application);
        ((MyApplication) application).getAppComponent().inject(this);
    }

    public LiveData<User> bindDataOrigin() {
        if (userLiveData == null) {
            userLiveData = new MutableLiveData<>();
        }
        return userLiveData;
    }

    public void updateUser(User user) {
        userLiveData.setValue(user);
    }
}

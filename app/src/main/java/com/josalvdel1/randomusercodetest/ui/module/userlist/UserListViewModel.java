package com.josalvdel1.randomusercodetest.ui.module.userlist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.josalvdel1.randomusercodetest.MyApplication;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {

    MutableLiveData<List<User>> userListLiveData;

    private boolean isLoading;
    private String searchTerm;

    public UserListViewModel(Application application) {
        super(application);
        ((MyApplication) application).getAppComponent().inject(this);
    }

    public LiveData<List<User>> bindDataOrigin() {
        if (userListLiveData == null) {
            userListLiveData = new MutableLiveData<>();
        }
        return userListLiveData;
    }

    public void updateUserList(List<User> users) {
        userListLiveData.setValue(users);
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isSearching() {
        return searchTerm != null;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
}
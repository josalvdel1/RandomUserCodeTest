package com.josalvdel1.randomusercodetest.presentation.ui.module.users;

import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.domain.usecase.user.GetUsers;
import com.josalvdel1.randomusercodetest.presentation.ui.Presenter;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UserListPresenter extends Presenter<UserListPresenter.View> {

    private GetUsers getUsers;

    @Inject
    public UserListPresenter(GetUsers getUsers) {
        super();
        this.getUsers = getUsers;
    }

    public void init() {
        attemptGetUsers();
    }

    private void attemptGetUsers() {
        showLoading();
        if (getView() != null) {
            getView().disableRefreshing();
        }
        getUsers.execute(new UseCase.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                hideLoading();
                if (getView() != null) {
                    getView().enableRefreshing();
                    if (result.size() > 0) {
                        getView().showUsers(result);
                    } else {
                        getView().showEmptyView();
                    }
                }
            }

            @Override
            public void onError(Throwable error) {
                hideLoading();
                if (getView() != null) {
                    getView().enableRefreshing();
                    getView().showGenericError();
                }
            }
        }, 20);
    }

    public void onUserClicked(User user) {
    }

    public void onRefreshUsers() {
        getView().clearUsers();
        attemptGetUsers();
    }

    public interface View extends Presenter.View {

        void showUsers(List<User> users);

        void clearUsers();

        void showEmptyView();

        void enableRefreshing();

        void disableRefreshing();

        void showGenericError();
    }
}

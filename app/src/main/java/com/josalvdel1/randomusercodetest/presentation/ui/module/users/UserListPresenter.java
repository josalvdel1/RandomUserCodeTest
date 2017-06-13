package com.josalvdel1.randomusercodetest.presentation.ui.module.users;

import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.domain.usecase.user.FetchMoreUsers;
import com.josalvdel1.randomusercodetest.domain.usecase.user.GetOldUsers;
import com.josalvdel1.randomusercodetest.presentation.ui.Presenter;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class UserListPresenter extends Presenter<UserListPresenter.View> {

    public static final int NEW_USERS_COUNT = 20;

    private UserListViewModel viewModel;
    private FetchMoreUsers fetchMoreUsers;
    private GetOldUsers getOldUsers;

    @Inject
    public UserListPresenter(FetchMoreUsers fetchMoreUsers, GetOldUsers getOldUsers) {
        super();
        this.fetchMoreUsers = fetchMoreUsers;
        this.getOldUsers = getOldUsers;
    }

    public void init(UserListViewModel viewModel, boolean activityJustCreated) {
        this.viewModel = viewModel;
        if (activityJustCreated) {
            getOldUsers();
        }
    }

    public void getOldUsers() {
        showLoading();
        getOldUsers.execute(new UseCase.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                hideLoading();
                viewModel.updateUserList(result);
            }

            @Override
            public void onError(Throwable error) {
                hideLoading();
            }
        });
    }

    private void fetchMoreUsers() {
        showLoading();
        fetchMoreUsers.execute(new UseCase.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                hideLoading();
                if (result.size() > 0) {
                    viewModel.updateUserList(result);
                } else {
                    getView().showEmptyView();
                }
            }

            @Override
            public void onError(Throwable error) {
                hideLoading();
                if (getView() != null) {
                    getView().showGenericError();
                }
            }
        }, NEW_USERS_COUNT);
    }

    public void onUserClicked(User user) {
    }

    public void onDeleteUserForeverClicked(User user) {
    }

    public void onQueryChange(String query) {
    }

    public void onLoadMoreClicked() {
        fetchMoreUsers();
    }

    public interface View extends Presenter.View {

        void showEmptyView();

        void showGenericError();
    }
}

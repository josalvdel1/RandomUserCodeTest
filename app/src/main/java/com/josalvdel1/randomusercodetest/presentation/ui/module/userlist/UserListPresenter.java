package com.josalvdel1.randomusercodetest.presentation.ui.module.userlist;

import android.support.annotation.NonNull;

import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.domain.usecase.user.DeleteUserForever;
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
    private DeleteUserForever deleteUserForever;

    @Inject
    public UserListPresenter(FetchMoreUsers fetchMoreUsers, GetOldUsers getOldUsers,
                             DeleteUserForever deleteUserForever) {
        super();
        this.fetchMoreUsers = fetchMoreUsers;
        this.getOldUsers = getOldUsers;
        this.deleteUserForever = deleteUserForever;
    }

    public void init(UserListViewModel viewModel, boolean activityJustCreated) {
        this.viewModel = viewModel;
        if (activityJustCreated) {
            getOldUsers();
        }
    }

    private void getOldUsers() {
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

    private void deleteUserForever(@NonNull User user) {
        deleteUserForever.execute(new UseCase.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                viewModel.updateUserList(result);
            }

            @Override
            public void onError(Throwable error) {
            }
        }, user);
    }

    public void onUserClicked(String userId) {
        getView().navigateToDetail(userId);
    }

    public void onDeleteUserForeverClicked(User user) {
        deleteUserForever(user);
    }

    public void onQueryChange(String query) {
    }

    public void onLoadMoreClicked() {
        fetchMoreUsers();
    }

    public interface View extends Presenter.View {

        void showEmptyView();

        void showGenericError();

        void navigateToDetail(String userId);
    }
}
package com.josalvdel1.randomusercodetest.presentation.ui.module.userlist;

import android.support.annotation.NonNull;

import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.domain.usecase.user.DeleteUserForever;
import com.josalvdel1.randomusercodetest.domain.usecase.user.FetchMoreUsers;
import com.josalvdel1.randomusercodetest.domain.usecase.user.GetOldUsers;
import com.josalvdel1.randomusercodetest.domain.usecase.user.GetUsersBySearch;
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
    private GetUsersBySearch getUsersBySearch;

    @Inject
    public UserListPresenter(FetchMoreUsers fetchMoreUsers, GetOldUsers getOldUsers,
                             DeleteUserForever deleteUserForever, GetUsersBySearch getUsersBySearch) {
        super();
        this.fetchMoreUsers = fetchMoreUsers;
        this.getOldUsers = getOldUsers;
        this.deleteUserForever = deleteUserForever;
        this.getUsersBySearch = getUsersBySearch;
    }

    public void init(UserListViewModel viewModel, boolean activityJustCreated) {
        this.viewModel = viewModel;
        if (activityJustCreated) {
            getOldUsers();
        }
    }

    private void getOldUsers() {
        showLoading();
        viewModel.setLoading(true);
        getOldUsers.execute(new UseCase.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                hideLoading();
                viewModel.setLoading(false);
                viewModel.updateUserList(result);
            }

            @Override
            public void onError(Throwable error) {
                viewModel.setLoading(false);
                hideLoading();
            }
        });
    }

    private void fetchMoreUsers() {
        viewModel.setLoading(true);
        showLoading();
        fetchMoreUsers.execute(new UseCase.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                viewModel.setLoading(false);
                hideLoading();
                if (result.size() > 0) {
                    viewModel.updateUserList(result);
                } else {
                    getView().showEmptyView();
                }
            }

            @Override
            public void onError(Throwable error) {
                viewModel.setLoading(false);
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
                if (!viewModel.isSearching()) {
                    viewModel.updateUserList(result);
                } else {
                    getUsersBySearch(viewModel.getSearchTerm());
                }
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
        getUsersBySearch(query);
        viewModel.setSearchTerm(query);
    }

    public void getUsersBySearch(String search) {
        showLoading();
        getUsersBySearch.execute(new UseCase.Callback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                hideLoading();
                viewModel.updateUserList(result);
            }

            @Override
            public void onError(Throwable error) {
                hideLoading();
            }
        }, search);
    }

    public void onLoadMoreClicked() {
        fetchMoreUsers();
    }

    public void onSearchCloseClicked() {
        viewModel.setSearchTerm(null);
        getOldUsers();
    }

    public interface View extends Presenter.View {

        void showEmptyView();

        void showGenericError();

        void navigateToDetail(String userId);
    }
}

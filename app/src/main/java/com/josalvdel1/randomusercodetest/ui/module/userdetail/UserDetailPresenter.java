package com.josalvdel1.randomusercodetest.ui.module.userdetail;

import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.domain.usecase.user.GetUser;
import com.josalvdel1.randomusercodetest.ui.Presenter;

import javax.inject.Inject;

@ActivityScope
public class UserDetailPresenter extends Presenter<UserDetailPresenter.View> {

    private UserDetailViewModel viewModel;
    private GetUser getUser;

    @Inject
    public UserDetailPresenter(GetUser getUser) {
        super();
        this.getUser = getUser;
    }

    public void init(UserDetailViewModel viewModel, boolean activityJustCreated, String userId) {
        this.viewModel = viewModel;
        getUser(userId);
    }

    private void getUser(String userId) {
        getUser.execute(new UseCase.Callback<User>() {
            @Override
            public void onSuccess(User result) {
                viewModel.updateUser(result);
            }

            @Override
            public void onError(Throwable error) {
            }
        }, userId);
    }

    public interface View extends Presenter.View {
    }
}

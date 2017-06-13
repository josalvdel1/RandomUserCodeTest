package com.josalvdel1.randomusercodetest.domain.usecase.user;


import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.executor.InteractorExecutor;
import com.josalvdel1.randomusercodetest.domain.executor.MainThread;
import com.josalvdel1.randomusercodetest.domain.repository.UserRepository;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.util.LogUtils;

import javax.inject.Inject;

public class GetUser extends UseCase<User> {

    private UserRepository userRepository;

    @Inject
    public GetUser(InteractorExecutor interactorExecutor, MainThread mainThread,
                   UserRepository userRepository) {
        super(interactorExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    public void onExecute(Callback<User> callback, Object... params) {
        try {
            String userId = (String) params[0];

            User user = userRepository.getUser(userId);

            notifySuccess(user, callback);
        } catch (Exception e) {
            notifyError(e, callback);
            LogUtils.logE("GetUsers", e.getMessage());
        }
    }
}

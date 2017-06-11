package com.josalvdel1.randomusercodetest.domain.usecase.user;


import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.executor.InteractorExecutor;
import com.josalvdel1.randomusercodetest.domain.executor.MainThread;
import com.josalvdel1.randomusercodetest.domain.repository.UserRepository;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.util.LogUtils;

import java.util.List;

import javax.inject.Inject;

public class GetUsers extends UseCase<List<User>> {

    private UserRepository userRepository;

    @Inject
    public GetUsers(InteractorExecutor interactorExecutor, MainThread mainThread,
                    UserRepository userRepository) {
        super(interactorExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    public void onExecute(Callback<List<User>> callback, Object... params) {
        try {
            Integer count = (Integer) params[0];

            //40 items by default
            List<User> promoCodes = userRepository.getUsers(count != null ? count : 40);

            notifySuccess(promoCodes, callback);
        } catch (Exception e) {
            notifyError(e, callback);
            LogUtils.logE("GetUsers", e.getMessage());
        }
    }
}

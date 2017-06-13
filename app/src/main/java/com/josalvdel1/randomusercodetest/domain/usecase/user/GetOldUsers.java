package com.josalvdel1.randomusercodetest.domain.usecase.user;


import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.executor.InteractorExecutor;
import com.josalvdel1.randomusercodetest.domain.executor.MainThread;
import com.josalvdel1.randomusercodetest.domain.repository.UserRepository;
import com.josalvdel1.randomusercodetest.domain.usecase.UseCase;
import com.josalvdel1.randomusercodetest.util.LogUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Retrieves all users that were previously fetched
 */
public class GetOldUsers extends UseCase<List<User>> {

    private UserRepository userRepository;

    @Inject
    public GetOldUsers(InteractorExecutor interactorExecutor, MainThread mainThread,
                       UserRepository userRepository) {
        super(interactorExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    public void onExecute(Callback<List<User>> callback, Object... params) {
        try {
            List<User> promoCodes = userRepository.getOldUsers();

            notifySuccess(promoCodes, callback);
        } catch (Exception e) {
            notifyError(e, callback);
            LogUtils.logE("GetUsers", e.getMessage());
        }
    }
}

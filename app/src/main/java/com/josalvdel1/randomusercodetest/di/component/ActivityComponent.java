package com.josalvdel1.randomusercodetest.di.component;

import com.josalvdel1.randomusercodetest.di.module.ActivityModule;
import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;
import com.josalvdel1.randomusercodetest.presentation.ui.module.users.UserListActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = {
                ActivityModule.class
        }
)
public interface ActivityComponent {

    void inject(UserListActivity activity);
}
package com.josalvdel1.randomusercodetest.di.component;

import com.josalvdel1.randomusercodetest.data.api.ApiModule;
import com.josalvdel1.randomusercodetest.di.module.ActivityModule;
import com.josalvdel1.randomusercodetest.di.module.AppModule;
import com.josalvdel1.randomusercodetest.di.module.ExecutorModule;
import com.josalvdel1.randomusercodetest.di.module.UIModule;
import com.josalvdel1.randomusercodetest.presentation.ui.BaseActivity;
import com.josalvdel1.randomusercodetest.presentation.ui.module.users.UserListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class,
                UIModule.class,
                ExecutorModule.class,
        }
)
public interface AppComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    // Activities
    void inject(BaseActivity activity);
}
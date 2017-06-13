package com.josalvdel1.randomusercodetest.di.component;

import com.josalvdel1.randomusercodetest.api.ApiModule;
import com.josalvdel1.randomusercodetest.di.module.ActivityModule;
import com.josalvdel1.randomusercodetest.di.module.AppModule;
import com.josalvdel1.randomusercodetest.di.module.DomainModule;
import com.josalvdel1.randomusercodetest.di.module.ExecutorModule;
import com.josalvdel1.randomusercodetest.di.module.UIModule;
import com.josalvdel1.randomusercodetest.presentation.ui.BaseActivity;
import com.josalvdel1.randomusercodetest.presentation.ui.module.userdetail.UserDetailViewModel;
import com.josalvdel1.randomusercodetest.presentation.ui.module.userlist.UserListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DomainModule.class,
                ApiModule.class,
                UIModule.class,
                ExecutorModule.class,
        }
)
public interface AppComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    // Activities
    void inject(BaseActivity activity);

    //ViewModels
    void inject(UserListViewModel viewModel);

    void inject(UserDetailViewModel viewModel);
}
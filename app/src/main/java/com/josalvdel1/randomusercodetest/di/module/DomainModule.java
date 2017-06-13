package com.josalvdel1.randomusercodetest.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.josalvdel1.randomusercodetest.domain.database.UserDao;
import com.josalvdel1.randomusercodetest.domain.database.UserDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    @Singleton
    public UserDatabase provideUserDb(Application application) {
        return Room.databaseBuilder(application, UserDatabase.class, "user.db").build();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(UserDatabase userDatabase) {
        return userDatabase.userDao();
    }
}

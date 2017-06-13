package com.josalvdel1.randomusercodetest.domain.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.josalvdel1.randomusercodetest.domain.converter.DateConverter;
import com.josalvdel1.randomusercodetest.domain.entity.User;

@Database(entities = {User.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}

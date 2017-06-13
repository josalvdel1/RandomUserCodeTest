package com.josalvdel1.randomusercodetest.domain.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<User> users);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();
}

package com.josalvdel1.randomusercodetest.domain.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<User> users);

    @Query("SELECT * FROM users WHERE id = :userId")
    User getUser(String userId);

    @Query("SELECT * FROM users WHERE NOT blackListed")
    List<User> getAllUsers();

    @Update
    void updateUser(User user);
}

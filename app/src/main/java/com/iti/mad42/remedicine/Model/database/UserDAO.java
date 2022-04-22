package com.iti.mad42.remedicine.Model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iti.mad42.remedicine.Model.pojo.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMedfriendUser(User user);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();
}

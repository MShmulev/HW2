package com.example.hw2.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE first_name=:firstName AND last_name=:lastName AND city=:city AND country=:country AND image=:image ")
    List<User> getUsers(String firstName, String lastName, String city, String country, String image);

}
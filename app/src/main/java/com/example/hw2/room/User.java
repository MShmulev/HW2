package com.example.hw2.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "users")

public class User{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public int userID;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "image")
    public String imageURL;





}
package com.example.bookstoreapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDAO {
    @Insert(onConflict = REPLACE)
    void insert(NameOfUser user);

    @Delete
    void delete(NameOfUser user);

    @Delete
    void reset(List<NameOfUser> users);

    @Query("SELECT * FROM Users_Names")
    List<NameOfUser> getAll();

}

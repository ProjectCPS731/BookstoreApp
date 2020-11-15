package com.example.bookstoreapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SearchDAO {
    @Insert
    void insert(RecentSearches user);

    @Delete
    void delete(RecentSearches user);

    @Delete
    void reset(List<RecentSearches> users);

    @Query("SELECT * FROM Users_Searches")
    List<RecentSearches> getAll();

    @Query("SELECT Recent_Searches FROM Users_Searches WHERE ID = :sID")
    String loadSearches(String sID);

    @Query("UPDATE Users_Searches SET Recent_Searches = :sSearches WHERE ID = :sID")
    void update(String sID, String sSearches);
}

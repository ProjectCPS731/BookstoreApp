package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "Users_Searches")
public class RecentSearches implements Serializable {
    @NonNull
    @PrimaryKey
    private String ID;
    @ColumnInfo(name = "Recent_Searches")
    private String Searches;

    @NonNull
    public String getID() {
        return ID;
    }

    public void setID(@NonNull String ID) {
        this.ID = ID;
    }

    public String getSearches() {
        return Searches;
    }

    public void setSearches(String searches) {
        Searches = searches;
    }

}

package com.example.bookstoreapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RecentSearches.class}, version = 1, exportSchema = false)
public abstract class RoomDB2 extends RoomDatabase {
    private static RoomDB2 database;

    private static String Database_Name = "Users_Searches";

    public synchronized static RoomDB2 getInstance(Context context)
    {
        if (database == null)
        {
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB2.class, Database_Name)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public abstract SearchDAO SearchDAO();
}

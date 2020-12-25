package com.example.myapp.DB.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class, Tag.class}, version = 1, exportSchema = false)
public abstract class appDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
    public abstract RecordDAO recordDAO();

    private static appDatabase instance = null;

    public static appDatabase getInstance(Context c) {
        if (instance == null) {
            synchronized (appDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(c.getApplicationContext(),
                            appDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return instance;

    }
}

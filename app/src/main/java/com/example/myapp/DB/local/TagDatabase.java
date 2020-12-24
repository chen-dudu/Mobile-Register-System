package com.example.myapp.DB.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Tag.class}, version = 1)
abstract class TagDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
}

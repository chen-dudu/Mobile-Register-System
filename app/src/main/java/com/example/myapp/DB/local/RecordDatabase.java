package com.example.myapp.DB.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Record.class}, version = 1)
abstract class RecordDatabase extends RoomDatabase {
    public abstract RecordDAO recordDao();
}

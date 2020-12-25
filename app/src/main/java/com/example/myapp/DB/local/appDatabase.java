package com.example.myapp.DB.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Record.class, Tag.class}, version = 1, exportSchema = false)
public abstract class appDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
    public abstract RecordDAO recordDAO();

    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(4);

    private static appDatabase instance = null;

    public static appDatabase getInstance(Context c) {
        if (instance == null) {
            synchronized (appDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(c.getApplicationContext(),
                            appDatabase.class, "app_database")
                            .addCallback(initialCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            dbExecutor.execute(() -> {
                TagDao tagDao = instance.tagDao();
                tagDao.deleteAll();

                tagDao.addTag(new Tag("标签A", "描述A"));
                tagDao.addTag(new Tag("标签B", "描述B"));
            });
        }
    };
}

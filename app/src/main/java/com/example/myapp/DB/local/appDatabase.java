package com.example.myapp.DB.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Record.class, Tag.class}, version = 2, exportSchema = false)
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
                            .addMigrations(MIGRATION_1_2)
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

                // mock tag data
                tagDao.addTag(new Tag("用户满意度调查", "0", "这个标签用于用户满意度相关的数据记录"));
                tagDao.addTag(new Tag("信号覆盖强度调查", "0", "这个标签用于信号强度相关的数据记录"));

                RecordDAO recordDAO = instance.recordDAO();
                recordDAO.deleteAll();

                // mock record data
                recordDAO.addRecord(new Record("0", "广西", "北海", "海城区", "北部湾西路", "1号", "信号覆盖强度：高", "信号覆盖强度调查"));
            });
        }
    };

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // "rename column" not available yer
//            database.execSQL("ALTER TABLE tag RENAME COLUMN Tag TO `Tag name`;");

            // update manually

            // first, create a temp table with the new schema
            database.execSQL("create table tag_new (`Tag name` TEXT NOT NULL, `Tag description` TEXT, primary key (`Tag name`));");

            // copy data
            database.execSQL("insert into tag_new (`Tag name`, `Tag description`) select Tag, `Tag description` from tag;");

            // remove old table
            database.execSQL("drop table tag");

            // rename new table
            database.execSQL("alter table tag_new rename to tag");
        }
    };
}

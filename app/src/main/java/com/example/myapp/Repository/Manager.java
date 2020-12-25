package com.example.myapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapp.DB.local.RecordDAO;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.DB.local.TagDao;
import com.example.myapp.DB.local.appDatabase;

import java.util.List;

public class Manager {

    private TagDao tagDao;
    private RecordDAO recordDAO;
    private appDatabase db;

    public Manager(Application application) {
        db = appDatabase.getInstance(application);
        tagDao = db.tagDao();
        recordDAO = db.recordDAO();
    }

    LiveData<List<Tag>> getAllTags() {
        return tagDao.getAllTags();
    }

    LiveData<Tag> getTag(String name) {
        return tagDao.getTag(name);
    }

    void addTag(Tag tag) {
        appDatabase.dbExecutor.execute(() -> {
            tagDao.addTag(tag);
        });
    }

    void deleteTag(Tag tag) {
        appDatabase.dbExecutor.execute(() -> {
            tagDao.deleteTag(tag);
        });
    }
}

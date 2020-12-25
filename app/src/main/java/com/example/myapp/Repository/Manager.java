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

    public LiveData<List<Tag>> getAllTags() {
        return tagDao.getAllTags();
    }

    public LiveData<Tag> getTag(Tag tag) {
        return tagDao.getTag(tag.name);
    }

    public void addTag(Tag tag) {
        appDatabase.dbExecutor.execute(() -> {
            tagDao.addTag(tag);
        });
    }

    public void deleteTag(Tag tag) {
        appDatabase.dbExecutor.execute(() -> {
            tagDao.deleteTag(tag);
        });
    }
}

package com.example.myapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DB.local.Record;
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

    public LiveData<Tag> getTag(String tagName) {
        return tagDao.getTag(tagName);
    }

    public void addTag(Tag tag) {
        appDatabase.dbExecutor.execute(() -> {
            tagDao.addTag(tag);
        });
    }

    public void updateDescription(Tag tag) {
        appDatabase.dbExecutor.execute(() -> {
            tagDao.updateDescription(tag);
        });
    }

    public void deleteTag(Tag tag) {
        appDatabase.dbExecutor.execute(() -> {
            tagDao.deleteTag(tag);
        });
    }

    /*--------------------------------------------------------------------------------------------*/

    public void addRecord(Record record) {
        appDatabase.dbExecutor.execute(() -> {
            recordDAO.addRecord(record);
        });
    }

    public LiveData<List<Record>> getAllRecords() {
        return recordDAO.getAllRecords();
    }

    public LiveData<List<Record>> getRecordsByTag(String tagName) {
        return recordDAO.getRecordsByTag(tagName);
    }

    public LiveData<Record> getRecordById(int id) {
        return recordDAO.getRecordById(id);
    }

    public void update(Record record) {
        appDatabase.dbExecutor.execute(() -> {
            recordDAO.update(record);
        });
    }

    public void deleteRecord(Record record) {
        appDatabase.dbExecutor.execute(() -> {
            recordDAO.deleteRecord(record);
        });
    }
}

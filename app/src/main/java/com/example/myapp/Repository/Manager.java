package com.example.myapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.RecordDAO;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.DB.local.TagCloud;
import com.example.myapp.DB.local.TagDao;
import com.example.myapp.DB.local.appDatabase;
import com.example.myapp.Util.RequestCallBack;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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

    // local
//    public void addTag(Tag tag) {
//        appDatabase.dbExecutor.execute(() -> {
//            tagDao.addTag(tag);
//        });
//    }

    // cloud
    public void addTag(Tag tag, RequestCallBack callBack) {
        TagCloud data = new TagCloud(tag.name, tag.description);
        data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                callBack.onComplete(e == null);
            }
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

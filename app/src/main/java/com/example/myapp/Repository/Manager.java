package com.example.myapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.RecordDAO;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.DB.local.TagCloud;
import com.example.myapp.DB.local.TagDao;
import com.example.myapp.DB.local.appDatabase;
import com.example.myapp.Util.RequestCallBack;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class Manager {

    private TagDao tagDao;
    private RecordDAO recordDAO;
    private appDatabase db;

    public Manager(Application application) {
        db = appDatabase.getInstance(application);
        tagDao = db.tagDao();
        recordDAO = db.recordDAO();
    }

    // local
//    public LiveData<List<Tag>> getAllTags() {
//        return tagDao.getAllTags();
//    }

    // cloud
    public LiveData<List<Tag>> getAllTags() {
        MutableLiveData<List<Tag>> result = new MutableLiveData<>();
//        List<Tag> tags = new ArrayList<>();
        result.postValue(null);
        String query = "select * from TagCloud order by name";
        new BmobQuery<TagCloud>().doSQLQuery(query, new SQLQueryListener<TagCloud>() {
            @Override
            public void done(BmobQueryResult<TagCloud> bmobQueryResult, BmobException e) {
                if (e == null) {
                    List<Tag> temp = new ArrayList<>();
                    for (TagCloud t : bmobQueryResult.getResults()) {
                        temp.add(new Tag(t.getName(), t.getObjectId(), t.getDescription()));
                    }
                    result.postValue(temp);
                }
            }
        });
        return result;
    }

    // local
//    public LiveData<Tag> getTag(String tagName) {
//        return tagDao.getTag(tagName);
//    }

    // cloud
    public LiveData<Tag> getTag(String name) {
        MutableLiveData<Tag> result = new MutableLiveData<>();
//        result.postValue(null);
        String query = "select * from TagCloud where name = ?";
        new BmobQuery<TagCloud>().doSQLQuery(query, new SQLQueryListener<TagCloud>() {
            @Override
            public void done(BmobQueryResult<TagCloud> bmobQueryResult, BmobException e) {
                if (e == null) {
                    if (bmobQueryResult.getResults().size() > 0) {
                        TagCloud temp = bmobQueryResult.getResults().get(0);
                        result.postValue(new Tag(temp.getName(), temp.getObjectId(), temp.getDescription()));
                    }
                    else {
                        // no match
                        result.postValue(null);
                    }
                }
            }
        }, name);
        return result;
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

    // local
//    public void updateDescription(Tag tag) {
//        appDatabase.dbExecutor.execute(() -> {
//            tagDao.updateDescription(tag);
//        });
//    }

    // cloud
    public void updateDescription(Tag tag, String description, RequestCallBack callBack) {
         TagCloud temp = new TagCloud(tag.name, description);
         temp.update(tag.id, new UpdateListener() {
             @Override
             public void done(BmobException e) {
                 callBack.onComplete(e == null);
             }
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

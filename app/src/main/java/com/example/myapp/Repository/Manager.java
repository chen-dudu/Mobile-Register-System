package com.example.myapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.RecordCloud;
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
import cn.bmob.v3.listener.QueryListener;
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
//        result.postValue(null);
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

    // local
//    public void deleteTag(Tag tag) {
//        appDatabase.dbExecutor.execute(() -> {
//            tagDao.deleteTag(tag);
//        });
//    }

    // cloud
    public void deleteTag(String id, RequestCallBack callBack) {
        TagCloud temp = new TagCloud("", "");
        temp.setObjectId(id);
        temp.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                callBack.onComplete(e == null);
            }
        });
    }

    /*--------------------------------------------------------------------------------------------*/

    // local
//    public void addRecord(Record record) {
//        appDatabase.dbExecutor.execute(() -> {
//            recordDAO.addRecord(record);
//        });
//    }

    // cloud
    public void addRecord(Record record, RequestCallBack callBack) {
        RecordCloud temp = new RecordCloud(record.province, record.city, record.district, record.road,
                                            record.detail, record.description, record.tag);
        temp.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                callBack.onComplete(e == null);
            }
        });
    }

    // local
//    public LiveData<List<Record>> getAllRecords() {
//        return recordDAO.getAllRecords();
//    }

    // cloud
    public LiveData<List<Record>> getAllRecords() {
        MutableLiveData<List<Record>> result = new MutableLiveData<>();
        String query = "select * from RecordCloud";
        System.out.println(">>> hhh");
        new BmobQuery<RecordCloud>().doSQLQuery(query, new SQLQueryListener<RecordCloud>() {
            @Override
            public void done(BmobQueryResult<RecordCloud> bmobQueryResult, BmobException e) {
                List<Record> temp = new ArrayList<>();
                if (e == null) {
                    for (RecordCloud r : bmobQueryResult.getResults()){
                        temp.add(new Record(r.getObjectId(), r.getProvince(), r.getCity(), r.getDistrict(),
                                            r.getRoad(), r.getDetail(), r.getDescription(), r.getTag()));
                    }
                }
                result.postValue(temp);
            }
        });
        return result;
    }

    // local
//    public LiveData<List<Record>> getRecordsByTag(String tagName) {
//        return recordDAO.getRecordsByTag(tagName);
//    }

    // cloud
    public LiveData<List<Record>> getRecordsByTag(String tagName) {
        MutableLiveData<List<Record>> result = new MutableLiveData<>();
        String query = "select * from RecordCloud where tag = ?";
        new BmobQuery<RecordCloud>().doSQLQuery(query, new SQLQueryListener<RecordCloud>() {
            @Override
            public void done(BmobQueryResult<RecordCloud> bmobQueryResult, BmobException e) {
                if (e == null) {
                    List<Record> temp = new ArrayList<>();
                    for (RecordCloud r : bmobQueryResult.getResults()) {
                        temp.add(new Record(r.getObjectId(), r.getProvince(), r.getCity(), r.getDistrict(),
                                            r.getRoad(), r.getDetail(), r.getDescription(), r.getTag()));
                    }
                    result.postValue(temp);
                }
            }
        }, tagName);
        return result;
    }

    // local
//    public LiveData<Record> getRecordById(int id) {
//        return recordDAO.getRecordById(id);
//    }

    // cloud
    public LiveData<Record> getRecordById(String id) {
        MutableLiveData<Record> result = new MutableLiveData<>();
        new BmobQuery<RecordCloud>().getObject(id, new QueryListener<RecordCloud>() {
            @Override
            public void done(RecordCloud recordCloud, BmobException e) {
                if (e == null) {
                    result.postValue(new Record(recordCloud.getObjectId(), recordCloud.getProvince(),
                            recordCloud.getCity(), recordCloud.getDistrict(), recordCloud.getRoad(),
                            recordCloud.getDetail(), recordCloud.getDescription(), recordCloud.getTag()));
                }
                else {
                    result.postValue(null);
                }
            }
        });
        return result;
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

package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.Repository.Manager;
import com.example.myapp.Util.RequestCallBack;

public class RecordUpdateStatusViewModel extends ViewModel {

    private Manager manager;

    public RecordUpdateStatusViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public LiveData<Record> getRecord(String id) {
        return manager.getRecordById(id);
    }

    public void update(Record record, RequestCallBack callBack) {
        manager.update(record, callBack);
    }
}

package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.Repository.Manager;

public class RecordDetailViewModel extends ViewModel {

    private Manager manager;

    public RecordDetailViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public LiveData<Record> getRecord(String id) {
        return manager.getRecordById(id);
    }

    public void delete(Record record) {
        manager.deleteRecord(record);
    }
}

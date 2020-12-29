package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.Repository.Manager;

import java.util.List;

public class RecordUpdateViewModel extends ViewModel {

    private Manager manager;

    public RecordUpdateViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public LiveData<Record> getRecord(int id) {
        return manager.getRecordById(id);
    }

    public LiveData<List<Tag>> getAllTags() {
        return manager.getAllTags();
    }

    public void update(Record record) {
        manager.update(record);
    }
}

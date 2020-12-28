package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.Repository.Manager;

import java.util.List;

public class CheckDisplayViewModel extends ViewModel {

    private Manager manager;

    public CheckDisplayViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public LiveData<List<Record>> getAllRecords() {
        return manager.getAllRecords();
    }

    public LiveData<List<Record>> getRecords(String tagName) {
        return manager.getRecordsByTag(tagName);
    }
}

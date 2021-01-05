package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.Repository.Manager;
import com.example.myapp.Util.RequestCallBack;

import java.util.List;

public class EnterViewModel extends ViewModel {

    private Manager manager;

    public EnterViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public void addRecord(Record record, RequestCallBack callBack) {
        manager.addRecord(record, callBack);
    }

    public LiveData<List<Tag>> getAllTags() {
        return manager.getAllTags();
    }
}

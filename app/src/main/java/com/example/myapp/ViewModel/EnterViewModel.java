package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.Repository.Manager;

public class EnterViewModel extends ViewModel {

    private Manager manager;

    public EnterViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public void addRecord(Record record) {
        manager.addRecord(record);
    }
}

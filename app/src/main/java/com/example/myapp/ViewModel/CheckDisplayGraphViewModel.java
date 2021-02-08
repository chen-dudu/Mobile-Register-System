package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.Repository.Manager;

import java.util.List;

public class CheckDisplayGraphViewModel extends ViewModel {

    private Manager manager;

    public CheckDisplayGraphViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public LiveData<List<Record>> getAllRecords() {
        return manager.getAllRecords();
    }
}

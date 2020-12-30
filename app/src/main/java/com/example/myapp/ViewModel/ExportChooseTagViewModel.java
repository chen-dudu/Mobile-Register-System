package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Record;
import com.example.myapp.DB.local.Tag;
import com.example.myapp.Repository.Manager;

import java.util.List;

public class ExportChooseTagViewModel extends ViewModel {

    private Manager manager;

    public ExportChooseTagViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public LiveData<List<Tag>> getAllTags() {
        return manager.getAllTags();
    }

    public LiveData<List<Record>> getRecordsByTag(String tagName) {
        return manager.getRecordsByTag(tagName);
    }
}

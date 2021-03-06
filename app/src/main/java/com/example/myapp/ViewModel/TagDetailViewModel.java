package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.Repository.Manager;
import com.example.myapp.Util.RequestCallBack;

public class TagDetailViewModel extends ViewModel {
    private Manager manager;

    public TagDetailViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public LiveData<Tag> getTag(String name) {
        return manager.getTag(name);
    }

    public void deleteTag(String id, RequestCallBack callBack) {
        manager.deleteTag(id, callBack);
    }
}

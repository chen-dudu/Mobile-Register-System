package com.example.myapp.ViewModel;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.Repository.Manager;
import com.example.myapp.Util.RequestCallBack;

public class NewDescriptionViewModel extends ViewModel {

    private Manager manager;

    public NewDescriptionViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public void updateDescription(Tag tag, String description, RequestCallBack callBack) {
        manager.updateDescription(tag, description, callBack);
    }

    public LiveData<Tag> getTag(String name) {
        return manager.getTag(name);
    }
}

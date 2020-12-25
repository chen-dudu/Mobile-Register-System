package com.example.myapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.Repository.Manager;

public class CreateTagViewModel extends ViewModel {

    private Manager manager;

    public CreateTagViewModel(Application application) {
        manager = new Manager(application);
    }

    public void addTag(Tag tag) {
        manager.addTag(tag);
    }
}

package com.example.myapp.ViewModel;


import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.myapp.DB.local.Tag;
import com.example.myapp.Repository.Manager;

public class NewDescriptionViewModel extends ViewModel {

    private Manager manager;

    public NewDescriptionViewModel(Application application) {
        this.manager = new Manager(application);
    }

    public void updateDescription(Tag tag) {
        manager.updateDescription(tag);
    }
}

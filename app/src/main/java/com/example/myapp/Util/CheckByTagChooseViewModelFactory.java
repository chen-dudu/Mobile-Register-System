package com.example.myapp.Util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.ViewModel.CheckByTagChooseViewModel;

public class CheckByTagChooseViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public CheckByTagChooseViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CheckByTagChooseViewModel(this.application);
    }
}

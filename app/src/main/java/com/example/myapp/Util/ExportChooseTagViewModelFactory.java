package com.example.myapp.Util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.ViewModel.ExportChooseTagViewModel;

public class ExportChooseTagViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public ExportChooseTagViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ExportChooseTagViewModel(this.application);
    }
}

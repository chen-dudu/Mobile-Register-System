package com.example.myapp.Util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.ViewModel.RecordUpdateStatusViewModel;

public class RecordUpdateStatusViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public RecordUpdateStatusViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RecordUpdateStatusViewModel(this.application);
    }
}

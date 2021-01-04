package com.example.myapp.Util;

import androidx.lifecycle.LiveData;

public interface ResultCallBack<T> {
    LiveData<T> onArrive(T t);
}

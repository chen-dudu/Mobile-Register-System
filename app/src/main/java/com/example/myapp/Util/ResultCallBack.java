package com.example.myapp.Util;

import androidx.lifecycle.LiveData;

/**
 * the interface for result callback, wrapping result in-between
 * @param <T> the type of the result
 */
public interface ResultCallBack<T> {
    LiveData<T> onArrive(T t);
}

package com.example.securelogin.util;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class SingleLiveData<T> extends MutableLiveData<T> {
    private static final String TAG = "SingleLiveData";

    private boolean mPending = false;

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                if (mPending) {
                    mPending = false;
                    observer.onChanged(t);
                }
            }
        });
    }

    @Override
    public void setValue(T t) {
        mPending = true;
        super.setValue(t);
    }

    @Override
    public void postValue(T t) {
        mPending = true;
        super.postValue(t);
    }
}

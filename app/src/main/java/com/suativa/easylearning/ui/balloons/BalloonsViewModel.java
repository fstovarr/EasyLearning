package com.suativa.easylearning.ui.balloons;

import android.graphics.Path;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BalloonsViewModel extends ViewModel {
    private MutableLiveData<Path[]> paths = new MutableLiveData<>();

    public MutableLiveData<Path[]> getPaths() {
        return paths;
    }
}

package com.suativa.easylearning.ui.listenimitate;

import android.app.Application;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.ListenImitateItem;
import com.suativa.easylearning.utils.RandomSorting;
import com.suativa.easylearning.utils.ThreadPoolExecutorHandler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ListenAndImitateViewModel extends AndroidViewModel {
    private MutableLiveData<ListenImitateItem[]> items;
    private int currentItemPosition;

    public ListenAndImitateViewModel(@NonNull Application application) {
        super(application);
    }

    int getCurrentItemPosition() {
        return currentItemPosition;
    }

    void incrementPosition() {
        this.currentItemPosition += 1;
    }

    void decrementPosition() {
        if (currentItemPosition - 1 >= 0)
            this.currentItemPosition -= 1;
    }

    MutableLiveData<ListenImitateItem[]> getItems() {
        if (items == null)
            items = new MutableLiveData<>();

        if (items.getValue() != null && items.getValue().length > 0)
            return items;

        String[] titles = getApplication().getResources().getStringArray(
                R.array.listen_imitate_titles);
        ListenImitateItem[] imitateItems = new ListenImitateItem[titles.length];

        Integer images[] = {R.drawable.smile, R.drawable.angry, R.drawable.laughter,
                R.drawable.sad, R.drawable.disgust};

        for (int i = 0; i < imitateItems.length; i++) {
            if (imitateItems[i] == null) imitateItems[i] = new ListenImitateItem();
            imitateItems[i].setTitle(titles[i]);
            imitateItems[i].setImageId(images[i]);
        }

        ThreadPoolExecutorHandler.getInstance().addThread(() ->
                RandomSorting.shuffleArray(imitateItems));

        items.setValue(imitateItems);

        return items;
    }
}

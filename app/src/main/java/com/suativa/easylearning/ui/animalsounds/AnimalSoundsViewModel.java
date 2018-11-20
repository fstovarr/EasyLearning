package com.suativa.easylearning.ui.animalsounds;

import android.app.Application;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.Animal;
import com.suativa.easylearning.model.AnimalSound;
import com.suativa.easylearning.utils.RandomSorting;
import com.suativa.easylearning.utils.ThreadPoolExecutorHandler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class AnimalSoundsViewModel extends AndroidViewModel {
    private MutableLiveData<AnimalSound[]> sounds;
    private MutableLiveData<Animal[]> animals;

    public AnimalSoundsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<AnimalSound[]> getSounds() {
        if (sounds == null) {
            AnimalSound[] animals = new AnimalSound[5];

            String[] names = getApplication().getResources()
                    .getStringArray(R.array.animal_sounds_names);

            int[] ids = new int[5];
            ids[0] = R.raw.littledogbark;
            ids[1] = R.raw.lionroar;
            ids[2] = R.raw.cat;
            ids[3] = R.raw.bird;
            ids[4] = R.raw.wolfshowling;

            for (int i = 0; i < animals.length; i++) {
                animals[i] = new AnimalSound();
                animals[i].setTitle(names[i]);
                animals[i].setImageId(R.drawable.whoanimal);
                animals[i].setResourceId(ids[i]);
            }

            sounds = new MutableLiveData<>();
            sounds.setValue(animals);
        }
        return sounds;
    }

    public MutableLiveData<Animal[]> getAnimals() {
        if (animals == null || animals.getValue() == null || animals.getValue().length == 0) {
            animals = new MutableLiveData<>();

            Animal[] animals = new Animal[5];

            for (int i = 0; i < animals.length; i++) {
                animals[i] = new Animal();
            }

            Integer images[] = {R.drawable.cat, R.drawable.birds, R.drawable.lion, R.drawable.wolf, R.drawable.dog};

            ThreadPoolExecutorHandler threads = ThreadPoolExecutorHandler.getInstance();
            threads.addThread(() -> {
                RandomSorting.shuffleArray(images);
                for (int i = 0; i < animals.length; i++) {
                    animals[i].setImageId(images[i]);
                }
            });

            this.animals.setValue(animals);
        }
        return animals;
    }
}

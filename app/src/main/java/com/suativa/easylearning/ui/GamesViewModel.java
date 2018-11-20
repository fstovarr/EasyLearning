package com.suativa.easylearning.ui;

import android.app.Application;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.Game;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class GamesViewModel extends AndroidViewModel {
    private MutableLiveData<Game[]> games;

    public GamesViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Game[]> getGames() {
        if (games == null)
            games = new MutableLiveData<>();

        if (games.getValue() != null && games.getValue().length > 0)
            return games;

        int sounds[] = {R.raw.balloons_instructions,
                R.raw.profession_instructions,
                R.raw.imitate_instructions,
                R.raw.vowels_instructions,
                R.raw.animalsounds_instructions};

        String descrptions[] = getApplication().getResources()
                .getStringArray(R.array.games_descriptions);

        Game[] game = new Game[5];
        for (int i = 0; i < game.length; i++) {
            if (game[i] == null) game[i] = new Game();
            game[i].setTitle("Nivel " + (i + 1));
            game[i].setLittleDescription(descrptions[i]);
            game[i].setDifficulty(i + 1);
            game[i].setImage(R.drawable.greenballoon);
            game[i].setInstrucionsId(sounds[i]);
        }

        games.setValue(game);

        return games;
    }
}

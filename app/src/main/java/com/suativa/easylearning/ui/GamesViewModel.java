package com.suativa.easylearning.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.Game;

public class GamesViewModel extends ViewModel {
    private MutableLiveData<Game[]> games;

    public MutableLiveData<Game[]> getGames() {
        if (games == null)
            games = new MutableLiveData<>();

        if (games.getValue() != null && games.getValue().length > 0)
            return games;

        Game[] game = new Game[5];
        for (int i = 0; i < game.length; i++) {
            if (game[i] == null) game[i] = new Game();
            game[i].setTitle("Nivel " + (i + 1));
            game[i].setLittleDescription("Descripcion");
            game[i].setDifficulty(i + 1);
            game[i].setImage(R.drawable.greenballoon);
        }

        games.setValue(game);

        return games;
    }
}

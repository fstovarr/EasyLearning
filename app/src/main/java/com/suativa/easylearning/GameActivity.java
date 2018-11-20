package com.suativa.easylearning;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.suativa.easylearning.model.Game;
import com.suativa.easylearning.ui.GamesViewModel;
import com.suativa.easylearning.ui.animalsounds.AnimalSoundsFragment;
import com.suativa.easylearning.ui.balloons.BalloonsFragment;
import com.suativa.easylearning.ui.drawvowels.DrawVowelsFragment;
import com.suativa.easylearning.ui.listenimitate.ListenAndImitateFragment;
import com.suativa.easylearning.ui.professions.ProfessionsFragment;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class GameActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        GamesViewModel viewModel = ViewModelProviders.of(this).get(GamesViewModel.class);

        int position = Objects.requireNonNull(getIntent().getExtras()).getInt("GAME_ID");
        Game game = viewModel.getGames().getValue()[position];

        if (game == null) return;

        TextView instructions = findViewById(R.id.game_instructions_label);
        instructions.setText(game.getLittleDescription());

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = BalloonsFragment.newInstance();
                break;
            case 1:
                fragment = ProfessionsFragment.newInstance();
                break;
            case 2:
                fragment = ListenAndImitateFragment.newInstance();
                break;
            case 3:
                fragment = DrawVowelsFragment.newInstance();
                break;
            case 4:
                fragment = AnimalSoundsFragment.newInstance();
                break;
        }

        if (fragment == null) return;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_content, fragment)
                .commitNow();

        mp = MediaPlayer.create(this, game.getInstrucionsId());

        FloatingActionButton fab = findViewById(R.id.fab_audio_button);
        fab.setOnClickListener(v -> mp.start());
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMediaPlayer();
    }

    private void stopMediaPlayer() {
        if (mp != null) {
            mp.pause();
            mp.seekTo(0);
        }
    }
}

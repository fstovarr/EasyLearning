package com.suativa.easylearning;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import com.suativa.easylearning.ui.GamesViewModel;
import com.suativa.easylearning.ui.main.ScrollGamesAdapter;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScrollGamesAdapter adapter;
    private GamesViewModel mViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
        mViewModel = ViewModelProviders.of(this).get(GamesViewModel.class);

        recyclerView = findViewById(R.id.games_recycler_view);
        recyclerView.setHasFixedSize(true);

        Intent i = new Intent(this, GameActivity.class);
        adapter = new ScrollGamesAdapter(
                Objects.requireNonNull(mViewModel.getGames().getValue()),
                (position, view1) -> {
                    i.putExtra("GAME_ID", position);
                    startActivity(i);
                });

        mViewModel.getGames().observe(this, (games) -> adapter.setGames(games));

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        mp = MediaPlayer.create(this, R.raw.main_instructions);

        ImageButton crab = findViewById(R.id.crab_button);
        crab.setOnClickListener(v -> mp.start());
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

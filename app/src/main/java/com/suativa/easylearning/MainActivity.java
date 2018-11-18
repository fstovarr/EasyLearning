package com.suativa.easylearning;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.suativa.easylearning.ui.GamesViewModel;
import com.suativa.easylearning.ui.main.ScrollGamesAdapter;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScrollGamesAdapter adapter;
    private GamesViewModel mViewModel;
    private RecyclerView.LayoutManager layoutManager;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

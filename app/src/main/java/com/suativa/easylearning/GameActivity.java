package com.suativa.easylearning;

import android.os.Bundle;
import android.widget.TextView;

import com.suativa.easylearning.ui.animalsounds.AnimalSoundsFragment;
import com.suativa.easylearning.ui.drawvowels.DrawVowelsFragment;
import com.suativa.easylearning.ui.listenimitate.ListenAndImitateFragment;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professions);

        TextView instructions = findViewById(R.id.game_instructions_label);
        int position = Objects.requireNonNull(getIntent().getExtras()).getInt("GAME_ID");

        Fragment fragment = null;
        switch (position) {
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
    }
}

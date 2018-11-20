package com.suativa.easylearning.ui.balloons;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.suativa.easylearning.R;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class BalloonsFragment extends Fragment {
    private BalloonsViewModel mViewModel;
    private ImageView balloons[];

    public static BalloonsFragment newInstance() {
        return new BalloonsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ballons, container, false);

        balloons = new ImageView[4];
        balloons[0] = view.findViewById(R.id.ballon1);
        balloons[1] = view.findViewById(R.id.ballon2);
        balloons[2] = view.findViewById(R.id.ballon3);
        balloons[3] = view.findViewById(R.id.ballon4);

        view.setOnClickListener((v) ->
                mViewModel.getPaths().setValue(buildPaths(balloons)));

        return view;
    }

    private Path[] buildPaths(View[] balloons) {
        Path paths[] = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paths = new Path[balloons.length];
            int initPoint[] = new int[2];

            ViewGroup.MarginLayoutParams vlp;

            for (int i = 0; i < balloons.length; i++) {
                if (paths[i] == null) paths[i] = new Path();
                balloons[i].getLocationOnScreen(initPoint);

                vlp = (ViewGroup.MarginLayoutParams) balloons[i].getLayoutParams();

                initPoint[0] -= (balloons[i].getMeasuredWidth() / 2 - vlp.leftMargin);
                initPoint[1] -= (balloons[i].getMeasuredHeight());

                for (int j = 0; j < 10; j++) {
                    initPoint[1] -= 200;

                    paths[i].arcTo(initPoint[0], initPoint[1], initPoint[0] + 100f,
                            initPoint[1] + 200f, 90f,
                            j % 2 == 0 ? -180f : 180f, true);
                }
            }
        }
        return paths;
    }

    private void buildAnimations(View[] balloons, Path[] paths) {
        ObjectAnimator animators[] = new ObjectAnimator[balloons.length];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Random delay = new Random();
            for (int i = 0; i < balloons.length; i++) {
                animators[i] = ObjectAnimator.ofFloat(balloons[i], View.X, View.Y, paths[i]);
                animators[i].setDuration(15000);
                animators[i].setStartDelay(delay.nextInt(1000 - 100) + 1500);
                animators[i].start();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BalloonsViewModel.class);
        mViewModel.getPaths().observe(this, paths -> buildAnimations(balloons, paths));
    }
}

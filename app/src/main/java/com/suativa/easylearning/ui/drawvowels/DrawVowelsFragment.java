package com.suativa.easylearning.ui.drawvowels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.byox.drawview.enums.BackgroundScale;
import com.byox.drawview.enums.BackgroundType;
import com.byox.drawview.views.DrawView;
import com.suativa.easylearning.R;
import com.suativa.easylearning.utils.ImageHandler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DrawVowelsFragment extends Fragment {
    public DrawVowelsFragment() {
        // Required empty public constructor
    }

    public static DrawVowelsFragment newInstance() {
        DrawVowelsFragment fragment = new DrawVowelsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draw_vowels, container, false);
        DrawView drawView = view.findViewById(R.id.draw_view);

        loadBackground(drawView);
        view.requestLayout();

        Button eraseButton = view.findViewById(R.id.btn_erase);
        eraseButton.setOnClickListener(v -> {
            drawView.restartDrawing();
            loadBackground(drawView);
        });

        return view;
    }

    private void loadBackground(DrawView drawView) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vowels_to_draw);
        Point sizeScreen = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(sizeScreen);
        Bitmap matrix = ImageHandler.getScaledDrawable(bitmap, sizeScreen.x, sizeScreen.y, ImageHandler.WIDTH_MATCH_PARENT, 5);

        drawView.post(() -> drawView.setBackgroundImage(matrix, BackgroundType.BITMAP, BackgroundScale.FIT_XY));

        drawView.getLayoutParams().width = matrix.getWidth();
        drawView.getLayoutParams().height = matrix.getHeight();
    }
}

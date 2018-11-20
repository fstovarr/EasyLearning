package com.suativa.easylearning.ui.animalsounds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView;
import com.suativa.easylearning.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalSoundsFragment extends Fragment {

    private AnimalSoundsViewModel mViewModel;
    private SoundsAdapter soundsAdapter;
    private AnimalsAdapter animalsAdapter;

    public static AnimalSoundsFragment newInstance() {
        return new AnimalSoundsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal_sounds, container, false);
        DragDropSwipeRecyclerView animalsGrid = view.findViewById(R.id.grid_animals);
        RecyclerView soundsGrid = view.findViewById(R.id.grid_sounds);

        soundsAdapter = new SoundsAdapter(null);
        soundsGrid.setAdapter(soundsAdapter);
        soundsGrid.setHasFixedSize(true);

        animalsAdapter = new AnimalsAdapter(null);
        animalsGrid.setAdapter(animalsAdapter);
        animalsGrid.setOrientation(DragDropSwipeRecyclerView
                .ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING);

        configureRecyclerView(soundsGrid);
        configureRecyclerView(animalsGrid);

        return view;
    }

    private void configureRecyclerView(RecyclerView gridLayout) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        gridLayout.setLayoutManager(manager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AnimalSoundsViewModel.class);
        mViewModel.getSounds().observe(this, sounds -> soundsAdapter.setSounds(sounds, getContext()));
        mViewModel.getAnimals().observe(this, animals -> animalsAdapter.setDataSet(animals));
    }

    @Override
    public void onPause() {
        super.onPause();
        soundsAdapter.stopMediaPlayerResources();
    }
}

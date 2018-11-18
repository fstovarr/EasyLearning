package com.suativa.easylearning.ui.listenimitate;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.ListenImitateItem;

import java.util.Objects;

public class ListenAndImitateFragment extends Fragment {
    private ListenAndImitateViewModel viewModel;

    public ListenAndImitateFragment() {
    }

    public static ListenAndImitateFragment newInstance() {
        ListenAndImitateFragment fragment = new ListenAndImitateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ListenAndImitateViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listen_and_imitate, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_expressions);
        Button next = view.findViewById(R.id.btn_next_expression);
        Button previous = view.findViewById(R.id.btn_previous_expression);

        ListenImitateItem[] items = viewModel.getItems().getValue();

        ListenAndImitateAdapter adapter = new ListenAndImitateAdapter(items, newPosition -> {
            if (newPosition == 0) previous.setVisibility(View.GONE);
            else previous.setVisibility(View.VISIBLE);

            if (newPosition == items.length - 1) next.setVisibility(View.GONE);
            else next.setVisibility(View.VISIBLE);
        });

        Pair<RecyclerView.LayoutManager, RecyclerView.SmoothScroller> pair =
                configureRecyclerView(recyclerView, adapter);

        viewModel.getItems().observe(this, adapter::setItems);

        next.setOnClickListener(v -> {
            viewModel.incrementPosition();
            smoothScrollTo(viewModel.getCurrentItemPosition(), pair.second, pair.first);
        });

        previous.setOnClickListener(v -> {
            viewModel.decrementPosition();
            smoothScrollTo(viewModel.getCurrentItemPosition(), pair.second, pair.first);
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (recyclerView.canScrollHorizontally(1)) {
                        next.setVisibility(View.VISIBLE);
                    } else {
                        next.setVisibility(View.GONE);
                    }

                    if (recyclerView.canScrollHorizontally(-1)) {
                        previous.setVisibility(View.VISIBLE);
                    } else {
                        previous.setVisibility(View.GONE);
                    }
                }
            }
        });

        return view;
    }

    private Pair<RecyclerView.LayoutManager, RecyclerView.SmoothScroller> configureRecyclerView(
            RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        RecyclerView.SmoothScroller scroller = new LinearSmoothScroller(
                Objects.requireNonNull(getContext())) {
            @Override
            protected int getHorizontalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        return new Pair(manager, scroller);
    }

    private void smoothScrollTo(int position, RecyclerView.SmoothScroller scroller,
                                RecyclerView.LayoutManager manager) {
        scroller.setTargetPosition(position);
        manager.startSmoothScroll(scroller);
    }
}

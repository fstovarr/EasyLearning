package com.suativa.easylearning.ui.animalsounds;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;

import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter;
import com.suativa.easylearning.model.Animal;

import java.util.Arrays;

import androidx.annotation.NonNull;

public class AnimalsAdapter extends DragDropSwipeAdapter<Animal, AnimalsAdapter.ViewHolder> {
    private Resources resources;

    public AnimalsAdapter(Animal[] animals) {
        if (animals != null)
            setDataSet(animals);
    }

    public void setDataSet(@NonNull Animal[] animals) {
        setDataSet(Arrays.asList(animals));
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        if (resources == null) resources = view.getContext().getResources();
        return new ViewHolder((ImageView) view);
    }

    @Override
    protected View getViewToTouchToStartDraggingItem(Animal s, ViewHolder viewHolder, int i) {
        return viewHolder.imageView;
    }

    @Override
    protected void onBindViewHolder(Animal s, ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageDrawable(resources.getDrawable(s.getImageId()));
    }

    public class ViewHolder extends DragDropSwipeAdapter.ViewHolder {
        private ImageView imageView;

        public ViewHolder(ImageView layout) {
            super(layout);
            imageView = layout;
        }
    }
}

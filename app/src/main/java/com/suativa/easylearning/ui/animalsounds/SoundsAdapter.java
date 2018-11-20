package com.suativa.easylearning.ui.animalsounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.AnimalSound;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SoundsAdapter extends RecyclerView.Adapter<SoundsAdapter.ViewHolder> {
    private AnimalSound[] sounds;
    private Context context;
    private List<AdapterView.OnItemClickListener> listeners;
    private MediaPlayer[] mediaPlayers;
    private int lastPosition = -1;

    public SoundsAdapter(AnimalSound[] sounds) {
        this.sounds = sounds;
        listeners = new ArrayList<>();

        addOnItemClickListener((parent, view, position, id) -> {
            stopMediaPlayerResources();
            mediaPlayers[position].start();
            lastPosition = position;
        });
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        freeMediaPlayerResources();
    }

    public void notifyItemClicked(AdapterView parent, ImageView imageView, int position) {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onItemClick(parent, imageView, position, imageView.getId());
        }
    }

    public void setSounds(AnimalSound[] sounds, Context context) {
        this.sounds = sounds;
        initializeMediaPlayers(sounds, context);
        notifyDataSetChanged();
    }

    private void initializeMediaPlayers(AnimalSound[] sounds, Context context) {
        if (sounds == null) return;
        mediaPlayers = new MediaPlayer[sounds.length];
        for (int i = 0; i < mediaPlayers.length; i++) {
            mediaPlayers[i] = MediaPlayer.create(context, sounds[i].getResourceId());
        }
    }

    public void stopMediaPlayerResources() {
        if (lastPosition != -1) {
            mediaPlayers[lastPosition].pause();
            mediaPlayers[lastPosition].seekTo(0);
        }
    }

    private void freeMediaPlayerResources() {
        if (mediaPlayers == null) return;

        stopMediaPlayerResources();

        for (int i = 0; i < mediaPlayers.length; i++) {
            mediaPlayers[i].release();
        }

        lastPosition = -1;
        mediaPlayers = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sounds, parent, false);
        if (context == null)
            context = parent.getContext();
        return new ViewHolder((CardView) inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(context.getResources().getDrawable(
                sounds[position].getImageId()));
    }

    @Override
    public int getItemCount() {
        return sounds.length;
    }

    public void addOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listeners.add(listener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imageView;

        public ViewHolder(CardView imageView) {
            super(imageView);
            this.imageView = imageView.findViewById(R.id.sound_image);
            this.imageView.setOnClickListener(v -> notifyItemClicked(
                    null, this.imageView, getAdapterPosition()));
        }
    }
}

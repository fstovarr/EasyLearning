package com.suativa.easylearning.ui.main;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.Game;

public class ScrollGamesAdapter extends RecyclerView.Adapter<ScrollGamesAdapter.GameViewHolder> {
    private static ClickListener listener;
    private Game[] games;

    public ScrollGamesAdapter(@NonNull Game[] games, ClickListener listener) {
        this.games = games;
        this.listener = listener;
    }

    public void setGames(Game[] games) {
        this.games = games;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_games_recyclerview,
                parent, false);
        return new GameViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.tvTitleGame.setText(games[position].getTitle());
        holder.tvDescriptionGame.setText(games[position].getLittleDescription());
        holder.rbDifficulty.setRating(games[position].getDifficulty());
        holder.ivIconGame.setImageDrawable(ContextCompat.getDrawable(
                holder.ivIconGame.getContext(), games[position].getImage()));
    }

    @Override
    public int getItemCount() {
        return games.length;
    }

    public interface ClickListener {
        void onClick(int position, View view);
    }

    static class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitleGame, tvDescriptionGame;
        private RatingBar rbDifficulty;
        private ImageView ivIconGame;

        GameViewHolder(CardView cardView) {
            super(cardView);
            cardView.setOnClickListener(this);
            tvTitleGame = cardView.findViewById(R.id.game_title_label);
            tvDescriptionGame = cardView.findViewById(R.id.game_description_label);
            rbDifficulty = cardView.findViewById(R.id.difficulty_bar_game);
            ivIconGame = cardView.findViewById(R.id.icon_game);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(getAdapterPosition(), v);
        }
    }
}

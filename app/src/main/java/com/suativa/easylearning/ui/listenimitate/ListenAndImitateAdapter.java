package com.suativa.easylearning.ui.listenimitate;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suativa.easylearning.R;
import com.suativa.easylearning.model.ListenImitateItem;

public class ListenAndImitateAdapter extends RecyclerView.Adapter<ListenAndImitateAdapter.ViewHolder> {
    private ListenImitateItem items[];
    private OnItemChangedListener listener;

    public ListenAndImitateAdapter(ListenImitateItem[] items, OnItemChangedListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public void setItems(ListenImitateItem[] items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_listenimitate_recyclerview, parent, false);
        return new ViewHolder(card);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        listener.change(position);
        holder.imageView.setImageResource(items[position].getImageId());
        holder.textView.setText(items[position].getTitle());
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public interface OnItemChangedListener {
        void change(int newPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_listenimitate);
            textView = itemView.findViewById(R.id.tv_listenimitate_title);
        }
    }
}

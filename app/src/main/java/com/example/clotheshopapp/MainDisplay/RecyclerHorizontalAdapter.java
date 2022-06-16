package com.example.clotheshopapp.MainDisplay;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.R;

import java.util.List;

public class RecyclerHorizontalAdapter extends RecyclerView.Adapter<RecyclerHorizontalAdapter.ViewHolder> {
    private static final String TAG = "RecyclerHorizontalAdapt";
    Context context;
    List<String> titleText;
    int selected_position = 0; // You have to set this globally in the Adapter class


    public RecyclerHorizontalAdapter(Context context, List<String> titleText) {
        this.context = context;
        this.titleText = titleText;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);

        return new ViewHolder(view);
    }


    int i = 0;
    CharSequence txt;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Here I am just highlighting the background
        holder.topGridLayout.setBackgroundColor(selected_position == position ? R.drawable.background_test : Color.WHITE);
        holder.topTextView.setTextColor(selected_position == position ? Color.BLACK : Color.GRAY);
        holder.titleTextView.setTextColor(selected_position == position ? Color.BLACK : Color.GRAY);
        holder.topImageView.setAlpha(selected_position == position ? 200: 50);

        txt = holder.topTextView.getText();
        String texts = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr," +
                " sed diam nonumy eirmod tempor invidunt ut labore et dolore "+
                " sed diam nonumy eirmod tempor invidunt ut labore et dolore "+
                " sed diam nonumy eirmod tempor invidunt ut labore et dolore ";
        holder.topTextView.setText(selected_position == position ? txt : txt);
        if (i % 2 == 0 && selected_position == position){
            holder.topTextView.setText(selected_position == position ? txt : txt);
        }else {
            holder.topTextView.setText(selected_position == position ? texts : txt);
        }
        i++;


    }


    @Override
    public int getItemCount() {
        return titleText.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView topTextView;
        TextView titleTextView;
        ImageView topImageView;
        GridLayout topGridLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            topTextView = itemView.findViewById(R.id.topTextView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            topImageView = itemView.findViewById(R.id.topImageView);
            topGridLayout = itemView.findViewById(R.id.topGridLayout);
        }

        @Override
        public void onClick(View v) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);
            // Do your another stuff for your onClick
        }


    }


}



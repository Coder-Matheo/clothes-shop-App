package com.example.clotheshopapp.MainDisplay;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.R;

import java.util.List;

public class RecyclerHorizontalAdapter extends RecyclerView.Adapter<RecyclerHorizontalAdapter.ViewHolder> {
    Context context;
    List<String> titleText;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return titleText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

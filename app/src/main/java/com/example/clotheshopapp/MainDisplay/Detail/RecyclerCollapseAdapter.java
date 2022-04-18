package com.example.clotheshopapp.MainDisplay.Detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.R;

import java.util.ArrayList;

public class RecyclerCollapseAdapter extends RecyclerView.Adapter<RecyclerCollapseAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> lstTitle ;

    public RecyclerCollapseAdapter(Context context, ArrayList<String> lstTitle) {
        this.context = context;
        this.lstTitle = lstTitle;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collapse_scroll_part, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lstTitle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

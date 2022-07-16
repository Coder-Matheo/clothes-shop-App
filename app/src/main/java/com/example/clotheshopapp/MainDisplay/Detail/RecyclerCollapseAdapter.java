package com.example.clotheshopapp.MainDisplay.Detail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.R;

import java.util.ArrayList;

public class RecyclerCollapseAdapter extends RecyclerView.Adapter<RecyclerCollapseAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> lstTitle;
    private String descriptionOfProduct;
    private String priceOfProduct;

    public RecyclerCollapseAdapter(Context context, ArrayList<String> lstTitle, String descriptionOfProduct1,
                                   String priceOfProduct1) {
        this.context = context;
        this.lstTitle = lstTitle;
        this.descriptionOfProduct = descriptionOfProduct1;
        this.priceOfProduct = priceOfProduct1;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collapse_scroll_part, parent, false);
        setValueToCollapseSameProduct(view);
        return new MyViewHolder(view);
    }

    private void setValueToCollapseSameProduct(View view) {
        TextView descriptionInRecyclerViewInCollapseTextView = view.findViewById(R.id.descriptionInRecyclerViewInCollapseTextView);
        TextView priceInRecyclerViewInCollapseTextView = view.findViewById(R.id.priceInRecyclerViewInCollapseTextView);

        descriptionInRecyclerViewInCollapseTextView.setText(descriptionOfProduct);
        priceInRecyclerViewInCollapseTextView.setText(priceOfProduct);

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

package com.example.clotheshopapp.MainDisplay.Detail;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private View view;
    private ArrayList<String> lstTitle;
    private Context context;
    public OnClickInterfaceAdapter onClickInterfaceAdapter;

    public RecyclerViewAdapter( Context context, ArrayList<String> lstTitle, OnClickInterfaceAdapter onClickInterfaceAdapter1) {
        this.lstTitle = lstTitle;
        this.context = context;
        this.onClickInterfaceAdapter = onClickInterfaceAdapter1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_product2, parent, false);


        /*if (viewType == 2){

        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_product2, parent, false);
        }*/

        return new MyViewHolder(view);
    }

    //Set Price and description to MainActivity
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lstTitle.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        if ((position+1) % 5 * 2 == 0){
            return 2;
        }else {
            return 1;
        }
    }*/

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layout;
        TextView descriptionItemTextView;
        TextView priceItemTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.linearLayoutId);
            descriptionItemTextView = itemView.findViewById(R.id.descriptionItemTextView);
            priceItemTextView = itemView.findViewById(R.id.priceItemTextView);

            //Clickable LinearLayout
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Adapter Class doesn't accept jumping mechanism, because we put it in DetailProduct Fragment
                    //For this reason, we use onClickInterface
                    onClickInterfaceAdapter.onClickListenerInterface(getAdapterPosition(),
                            descriptionItemTextView, priceItemTextView);

                }
            });
        }
    }
}

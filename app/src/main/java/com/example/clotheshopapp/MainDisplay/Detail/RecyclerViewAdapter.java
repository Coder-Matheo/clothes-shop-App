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

        Log.i("TAG", "onCreateViewHolder: "+viewType);
        if (viewType == 2){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_product, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_product2, parent, false);
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.description_item_textView.setText(lstTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return lstTitle.size();
    }


    @Override
    public int getItemViewType(int position) {
        if ((position+1) % 5 * 2 == 0){
            return 2;
        }else {
            return 1;
        }
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layout;
        TextView description_item_textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.linearLayoutId);
            description_item_textView = itemView.findViewById(R.id.description_item_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterfaceAdapter.onClickListenerInterface(getAdapterPosition());
                }
            });
        }


    }


}

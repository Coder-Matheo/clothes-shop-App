package com.example.clotheshopapp.MainDisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.MainDisplay.Detail.OnClickInterfaceAdapter;
import com.example.clotheshopapp.MainDisplay.SameFeature.RunnableCountDownTimer;
import com.example.clotheshopapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private View view;
    private ArrayList<String> lstTitle;
    ArrayList<String> lstProductPrice = new ArrayList<>();
    ArrayList<String> lstProductName = new ArrayList<>();
    ArrayList<String> lstProductDataOff = new ArrayList<>();
    ArrayList<byte[]> lstProductImg = new ArrayList<>();
    private Context context;
    public OnClickInterfaceAdapter onClickInterfaceAdapter;
    private static final String TAG = "RecyclerViewAdapter";





    public RecyclerViewAdapter(Context context, ArrayList<String> lstTitle,ArrayList<String> lstProductName,
                               ArrayList<String> lstProductPrice, ArrayList<String> lstProductDataOff,
                               ArrayList<byte[]> lstProductImg,OnClickInterfaceAdapter onClickInterfaceAdapter1) {

        this.lstTitle = lstTitle;
        this.lstProductPrice = lstProductPrice;
        this.lstProductName = lstProductName;
        this.lstProductDataOff = lstProductDataOff;
        this.lstProductImg = lstProductImg;
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
        RunnableCountDownTimer timer = new RunnableCountDownTimer(view.getContext());

        int time = 0;

        try {
            time = Integer.valueOf(lstProductDataOff.get(position));

            timer.countDownTimer(time, holder.timerItemTextView);
        }catch (Exception e){
            e.printStackTrace();
        }


        holder.priceItemTextView.setText(lstProductPrice.get(position));
        holder.descriptionItemTextView.setText(lstProductName.get(position));
        holder.productImageView.setImageResource(R.drawable.imag1);


    }

    @Override
    public int getItemCount() {
        return 5;
    }


    /*@Override
    public int getItemViewType(int position) {
        if ((position+1) % 5 * 2 == 0){
            return 2;
        }else {
            return 1;
        }
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView descriptionItemTextView;
        TextView priceItemTextView;
        TextView timerItemTextView;
        ImageView productImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            descriptionItemTextView = itemView.findViewById(R.id.descriptionItemTextView);
            priceItemTextView = itemView.findViewById(R.id.priceItemTextView);
            timerItemTextView = itemView.findViewById(R.id.timerItemTextView);
            productImageView = itemView.findViewById(R.id.productImageView);

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

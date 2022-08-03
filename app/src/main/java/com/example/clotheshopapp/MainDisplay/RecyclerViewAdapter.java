package com.example.clotheshopapp.MainDisplay;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.MainDisplay.Detail.OnClickInterfaceAdapter;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataConverter;
import com.example.clotheshopapp.MainDisplay.SameFeature.RunnableCountDownTimer;
import com.example.clotheshopapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private View view;
    private ArrayList<String> lstProductPrice = new ArrayList<>();
    private ArrayList<String> lstProductName = new ArrayList<>();
    private ArrayList<String> lstProductDataOff = new ArrayList<>();
    private ArrayList<byte[]> lstProductImg = new ArrayList<>();
    private LifecycleOwner lifecycleOwner;
    public OnClickInterfaceAdapter onClickInterfaceAdapter;
    private static final String TAG = "RecyclerViewAdapter";


    public RecyclerViewAdapter(LifecycleOwner lifecycleOwner, ArrayList<String> lstProductName,
                               ArrayList<String> lstProductPrice, ArrayList<String> lstProductDataOff,
                               ArrayList<byte[]> lstProductImg, OnClickInterfaceAdapter onClickInterfaceAdapter1) {

        this.lstProductPrice = lstProductPrice;
        this.lstProductName = lstProductName;
        this.lstProductDataOff = lstProductDataOff;
        this.lstProductImg = lstProductImg;
        this.lifecycleOwner = lifecycleOwner;
        this.onClickInterfaceAdapter = onClickInterfaceAdapter1;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
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
        try {
            int time = 0;
            time = Integer.valueOf(lstProductDataOff.get(position));
            timer.countDownTimer(time, holder.timerItemTextView);
        }catch (Exception e){
            e.printStackTrace();
        }
        DataConverter dataConverter = new DataConverter();
        holder.productImageView.setImageBitmap(
                dataConverter.convertByteArray2Image(lstProductImg.get(position)));
        holder.priceItemTextView.setText(lstProductPrice.get(position));
        holder.nameOfProductTextView.setText(lstProductName.get(position));
    }

    @Override
    public int getItemCount() {
        return lstProductName.size();
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

        TextView nameOfProductTextView;
        TextView priceItemTextView;
        TextView timerItemTextView;
        ImageView productImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            nameOfProductTextView = itemView.findViewById(R.id.nameOfProductTextView);
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
                            nameOfProductTextView, priceItemTextView, productImageView);

                }
            });
        }
    }
}

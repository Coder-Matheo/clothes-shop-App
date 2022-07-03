package com.example.clotheshopapp.MainDisplay;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.MainDisplay.Detail.OnClickInterfaceAdapter;
import com.example.clotheshopapp.MainDisplay.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.Retrofit1.RetrofitService1;
import com.example.clotheshopapp.MainDisplay.Retrofit1.ServerApi;
import com.example.clotheshopapp.MainDisplay.SameFeature.RunnableCountDownTimer;
import com.example.clotheshopapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private View view;
    private ArrayList<String> lstTitle;
    ArrayList<String> lstProductPrice = new ArrayList<>();
    ArrayList<String> lstProductName = new ArrayList<>();
    ArrayList<String> lstProductDataOff = new ArrayList<>();
    private Context context;
    public OnClickInterfaceAdapter onClickInterfaceAdapter;
    private static final String TAG = "RecyclerViewAdapter";





    public RecyclerViewAdapter(Context context, ArrayList<String> lstTitle,ArrayList<String> lstProductName,
                               ArrayList<String> lstProductPrice, ArrayList<String> lstProductDataOff,OnClickInterfaceAdapter onClickInterfaceAdapter1) {

        this.lstTitle = lstTitle;
        this.lstProductPrice = lstProductPrice;
        this.lstProductName = lstProductName;
        this.lstProductDataOff = lstProductDataOff;
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
        timer.countDownTimer(Integer.valueOf(lstProductDataOff.get(position)), holder.timerItemTextView);

        holder.priceItemTextView.setText(lstProductPrice.get(position));
        holder.descriptionItemTextView.setText(lstProductName.get(position));


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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView descriptionItemTextView;
        TextView priceItemTextView;
        TextView timerItemTextView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            descriptionItemTextView = itemView.findViewById(R.id.descriptionItemTextView);
            priceItemTextView = itemView.findViewById(R.id.priceItemTextView);
            timerItemTextView = itemView.findViewById(R.id.timerItemTextView);

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

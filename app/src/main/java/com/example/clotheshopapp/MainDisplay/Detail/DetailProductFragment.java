package com.example.clotheshopapp.MainDisplay.Detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RecyclerViewAdapter;
import com.example.clotheshopapp.MainDisplay.Retrofit1.RetrofitService1;
import com.example.clotheshopapp.MainDisplay.Retrofit1.ServerApi;
import com.example.clotheshopapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailProductFragment extends Fragment implements OnClickInterfaceAdapter{

    private static final String TAG = "DetailProductFragment";
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_product, container, false);

        setGetDBServerValueAndSetRecyclerView(view);

        return view;
    }


    private void setGetDBServerValueAndSetRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.recyclerViewInTab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> lstTitle = new ArrayList<>();
        ArrayList<String> lstProductPrice = new ArrayList<>();
        ArrayList<String> lstProductDataOff = new ArrayList<>();
        ArrayList<byte[]> lstProductImg = new ArrayList<>();
        ArrayList<String> lstProductName = new ArrayList<>();

        //Initial Retrofit
        RetrofitService1 retrofitService1 = new RetrofitService1();
        //ServerApi serverApi = retrofitService1.getRetrofit().create(ServerApi.class);


        /*serverApi.getAllProduct()
                .enqueue(new Callback<List<ProductData>>() {
                    @Override
                    public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {



                        for (int i = 0; i < response.body().size(); i++){

                            lstTitle.add(String.valueOf(response.body().get(i).getBid()));
                            lstProductName.add(response.body().get(i).getProductName());
                            lstProductPrice.add(response.body().get(i).getProductPrice());
                            lstProductDataOff.add(response.body().get(i).getProduct_dateOff());
                            lstProductImg.add(response.body().get(i).getProduct_image());


                        }


                        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstTitle,lstProductName,lstProductPrice,
                                lstProductDataOff,lstProductImg, DetailProductFragment.this::onClickListenerInterface);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

                        //Set span size
                        /*gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if ((position+1) % 5 * 2 == 0){
                                    return 2;
                                }else {
                                    return 1;
                                }
                            }
                        });*/
                        /*recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(recyclerViewAdapter);
                        recyclerViewAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<ProductData>> call, Throwable t) {
                    }
                });*/
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClickListenerInterface(int position, TextView descriptionProduct, TextView priceOfProduct) {


        //Transfer Data through Bundle
        Bundle mDataBundle = new Bundle();
        mDataBundle.putString("descriptionOfProductBundle", descriptionProduct.getText().toString());
        mDataBundle.putString("priceOfProductBundle", priceOfProduct.getText().toString());

        collapseDetailProductFragment collapseDetailProductFragment = new collapseDetailProductFragment();
        collapseDetailProductFragment.setArguments(mDataBundle);

        //Mechanism to jump to detail
        //addToBackStack help you to back last jumping Fragment/Activity
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, collapseDetailProductFragment)
                .addToBackStack(null)
                .commit();
    }
}

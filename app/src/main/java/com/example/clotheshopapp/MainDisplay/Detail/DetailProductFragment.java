package com.example.clotheshopapp.MainDisplay.Detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.MainDisplay.RecyclerViewAdapter;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.ManipulateValue.ManipulateValueUser;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;
import com.example.clotheshopapp.R;

import java.util.ArrayList;
import java.util.List;


public class DetailProductFragment extends Fragment implements OnClickInterfaceAdapter{

    private static final String TAG = "DetailProductFragment";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> lstTitle = new ArrayList<>();
    private ArrayList<String> lstProductPrice = new ArrayList<>();
    private ArrayList<String> lstProductDataOff = new ArrayList<>();
    private ArrayList<byte[]> lstProductImg = new ArrayList<>();
    private ArrayList<String> lstProductName = new ArrayList<>();
    private View view;

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



        //manipulateValueUser.findUserWithNamePass("Matheo", "Matheo1370?!");

        LiveData<List<ProductData>> proList = MySingletonProduct.getInstance(getContext())
                .productDao()
                .getAllProducts();
        proList.observe(getViewLifecycleOwner(), new Observer<List<ProductData>>() {
            @Override
            public void onChanged(List<ProductData> productData) {

                for (int i = 0; i < productData.size(); i++){
                    lstTitle.add(productData.get(i).getProductName());
                    lstProductName.add(productData.get(i).getProductName());
                    lstProductDataOff.add(productData.get(i).getProduct_dateOff());
                    lstProductPrice.add(productData.get(i).getProductPrice());
                }
                recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstProductName,lstProductPrice,
                        lstProductDataOff,lstProductImg, DetailProductFragment.this::onClickListenerInterface);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

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


        //Initial Retrofit
        //RetrofitService1 retrofitService1 = new RetrofitService1();
        //ServerApi serverApi = retrofitService1.getRetrofit().create(ServerApi.class);


        /*serverApi.getAllProduct()
                .enqueue(new Callback<List<ProductData>>() {
                    @Override
                    public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<ProductData>> call, Throwable t) {
                    }
                });*/
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

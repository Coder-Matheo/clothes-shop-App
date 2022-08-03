package com.example.clotheshopapp.MainDisplay.Detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataViewModel;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChildProductFragment extends Fragment implements OnClickInterfaceAdapter{
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> lstTitle = new ArrayList<>();
    private ArrayList<String> lstProductPrice = new ArrayList<>();
    private ArrayList<String> lstProductDataOff = new ArrayList<>();
    private ArrayList<byte[]> lstProductImg = new ArrayList<>();
    private ArrayList<String> lstProductName = new ArrayList<>();
    private View view;
    private DataViewModel dataViewModel;
    private static final String TAG = "ChildProductFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child_product, container, false);

        dataViewModel = new DataViewModel(getActivity().getApplication());

        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        recyclerView = view.findViewById(R.id.childFragmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LiveData<List<ProductData>> proList = dataViewModel.getAllProductQuery();


        proList.observe(getViewLifecycleOwner(), new Observer<List<ProductData>>() {
            @Override
            public void onChanged(List<ProductData> productData) {

                for (int i = 0; i < productData.size(); i++){
                    if (productData.get(i).getClassified_product().equals("KIND")){
                        lstTitle.add(productData.get(i).getProductName());
                        lstProductName.add(productData.get(i).getProductName());
                        lstProductDataOff.add(productData.get(i).getProduct_dateOff());
                        lstProductPrice.add(productData.get(i).getProductPrice());
                        lstProductImg.add(productData.get(i).getProduct_image());
                    }
                }
                recyclerViewAdapter = new RecyclerViewAdapter(getViewLifecycleOwner(), lstProductName,lstProductPrice,
                        lstProductDataOff,lstProductImg, ChildProductFragment.this::onClickListenerInterface);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onClickListenerInterface(int position, TextView descriptionProduct, TextView priceOfProduct, ImageView productImageView) {
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

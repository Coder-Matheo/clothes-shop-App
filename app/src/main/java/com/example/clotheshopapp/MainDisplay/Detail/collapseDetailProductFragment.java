package com.example.clotheshopapp.MainDisplay.Detail;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.R;

import java.util.ArrayList;

public class collapseDetailProductFragment extends Fragment {
    View view;
    RecyclerView collapseRecyclerView;
    RecyclerCollapseAdapter recyclerCollapseAdapter;
    String descriptionOfProductValue;
    String priceOfProductValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collapse_detail_product, container, false);


        getBundleValueFromMainSetValue(view);
        setCollapseHorizontalRecyclerView(view, getContext());
        return view;
    }

    private void getBundleValueFromMainSetValue(View view) {

        TextView descriptionCollapseUITextView = view.findViewById(R.id.descriptionCollapseTextView);
        TextView priceCollapseUITextView = view.findViewById(R.id.descriptionCollapseTextView);


        //Receive Value from the DetailProduct Fragment through Bundle
        descriptionOfProductValue = getArguments().getString("descriptionOfProductBundle");
        priceOfProductValue = getArguments().getString("priceOfProductBundle");
        descriptionCollapseUITextView.setText("Value Of "+ descriptionOfProductValue);
        priceCollapseUITextView.setText(priceOfProductValue);
        Log.i("Tag In Collapse in ", descriptionOfProductValue+" : "+ priceOfProductValue);
    }


    private void setCollapseHorizontalRecyclerView(View view, Context context) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            arrayList.add("Item "+i);
        }



        collapseRecyclerView = view.findViewById(R.id.collapseHorizontalRecyclerView);
        recyclerCollapseAdapter = new RecyclerCollapseAdapter(context, arrayList, descriptionOfProductValue, priceOfProductValue);
        collapseRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        collapseRecyclerView.setAdapter(recyclerCollapseAdapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}

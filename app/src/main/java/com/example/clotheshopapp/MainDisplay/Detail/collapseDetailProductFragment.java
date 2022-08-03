package com.example.clotheshopapp.MainDisplay.Detail;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
    Drawable imageDraw;



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
        imageDraw = (Drawable) getArguments().getSerializable("image");

        descriptionCollapseUITextView.setText("Value Of "+ descriptionOfProductValue);
        priceCollapseUITextView.setText(priceOfProductValue);
        Log.i("Tag In Collapse in ", descriptionOfProductValue+" : "+ priceOfProductValue+ " : "+
                imageDraw);
    }


    private void setCollapseHorizontalRecyclerView(View view, Context context) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            arrayList.add("Item "+i);
        }

        collapseRecyclerView = view.findViewById(R.id.collapseHorizontalRecyclerView);
        recyclerCollapseAdapter = new RecyclerCollapseAdapter(context, arrayList, descriptionOfProductValue, priceOfProductValue);
        collapseRecyclerView.setLayoutManager(new GridLayoutManager(context,  2));
        collapseRecyclerView.setAdapter(recyclerCollapseAdapter);

    }


}

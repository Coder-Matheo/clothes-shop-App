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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clotheshopapp.R;

import java.util.ArrayList;

public class DetailProductFragment extends Fragment implements OnClickInterfaceAdapter{

    private static final String TAG = DetailProductFragment.class.getSimpleName();
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewInTab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> lstTitle = new ArrayList<>();
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        lstTitle.add("Hallo");
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstTitle, this::onClickListenerInterface);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        Log.i(TAG, "onCreateView: ");
        //Set span size
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ((position+1) % 5 * 2 == 0){
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClickListenerInterface(int position, TextView descriptionProduct, TextView priceOfProduct) {
        Log.i(TAG, "onClickListener: "+ position + " : "+ descriptionProduct +" : "+ priceOfProduct);

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

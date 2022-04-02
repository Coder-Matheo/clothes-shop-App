package com.example.clotheshopapp.MainDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.clotheshopapp.MainDisplay.Detail.DetailProduct;
import com.example.clotheshopapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewHorizontal;
    RecyclerHorizontalAdapter recyclerHorizontalAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Toolbar topToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topToolbar = findViewById(R.id.topTollBar);



        setRecyclerViewHorizontal();
        setTabLayout();
    }

    public void setRecyclerViewHorizontal(){
        recyclerViewHorizontal = findViewById(R.id.recyclerview_horizontal);

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            arrayList.add("Item "+i);
        }

        recyclerHorizontalAdapter = new RecyclerHorizontalAdapter(this, arrayList);
        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewHorizontal.setAdapter(recyclerHorizontalAdapter);
    }

    public void setTabLayout(){
        ArrayList<String> titleTabLayout = new ArrayList<>();

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new DetailProduct(), "Men");
        viewPagerAdapter.addFragment(new DetailProduct(), "Women");
        viewPagerAdapter.addFragment(new DetailProduct(), "Children");
        viewPagerAdapter.addFragment(new DetailProduct(), "Men");
        viewPagerAdapter.addFragment(new DetailProduct(), "Men");
        viewPagerAdapter.addFragment(new DetailProduct(), "Men");
        viewPagerAdapter.addFragment(new DetailProduct(), "Men");


        titleTabLayout.add("Basic");
        titleTabLayout.add("Basic");
        titleTabLayout.add("Basic");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

}
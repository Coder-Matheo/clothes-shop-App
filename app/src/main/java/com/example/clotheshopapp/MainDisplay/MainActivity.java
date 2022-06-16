package com.example.clotheshopapp.MainDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.clotheshopapp.MainDisplay.Account.AccountFragment;
import com.example.clotheshopapp.MainDisplay.Detail.DetailProductFragment;
import com.example.clotheshopapp.MainDisplay.login.LoggingActivity;
import com.example.clotheshopapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewHorizontal;
    RecyclerHorizontalAdapter recyclerHorizontalAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Toolbar topToolbar;
    CircleImageView accountImageButton;
    private static final String TAG = "MainActivity";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topToolbar = findViewById(R.id.topTollBar);
        //topToolbar.setSubtitle("Test Subtitle");





        setRecyclerViewHorizontal();
        setButtonAccountFragment();
        setTabLayout();
        setOptionsMenuSelect();
        timeCountdown(30000);
    }



    private void setButtonAccountFragment() {
        accountImageButton = findViewById(R.id.accountImageButton);
        accountImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right, android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container_view,
                        new AccountFragment()).commit();

            }
        });
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

        viewPagerAdapter.addFragment(new DetailProductFragment(), "Men");
        viewPagerAdapter.addFragment(new DetailProductFragment(), "Women");
        viewPagerAdapter.addFragment(new DetailProductFragment(), "Children");
        viewPagerAdapter.addFragment(new DetailProductFragment(), "Men");
        viewPagerAdapter.addFragment(new DetailProductFragment(), "Men");
        viewPagerAdapter.addFragment(new DetailProductFragment(), "Men");
        viewPagerAdapter.addFragment(new DetailProductFragment(), "Men");


        titleTabLayout.add("Basic");
        titleTabLayout.add("Basic");
        titleTabLayout.add("Basic");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        //Detect selected Tab in tabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i(TAG, "onTabSelected: "+tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    private void timeCountdown(int secondCountdown) {
        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long l) {
                Log.i(TAG, "onTick: "+l);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Off Finished");

            }
        }.start();

    }


    private void setOptionsMenuSelect() {
        topToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i(TAG, "onMenuItemClick: "+item.getItemId());
                switch (item.getItemId()){
                    case R.id.signUp_logging_main_menu:
                        Intent intent = new Intent(getApplicationContext(), LoggingActivity.class);

                        startActivity(intent);
                        return true;
                    case R.id.warenkorb_main_menu:
                        Log.i(TAG, "onMenuItemClick: warenkorb");
                        return true;
                }
                return false;
            }
        });
    }



    //for access Image Phone
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //call super
        super.onActivityResult(requestCode, resultCode, data);
    }


}


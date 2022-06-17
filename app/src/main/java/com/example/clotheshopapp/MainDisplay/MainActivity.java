package com.example.clotheshopapp.MainDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    TextView usernameMainTextView;

    int i;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topToolbar = findViewById(R.id.topTollBar);


        setRecyclerViewHorizontal();
        setButtonAccountFragment();
        setTabLayout();
        setOptionsMenuSelect();
        setUsernameOfToolbar();

    }

    private void setUsernameOfToolbar() {
        //need get Data from Database
        usernameMainTextView = findViewById(R.id.usernameMainTextView);
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
                    case R.id.topOffButton:
                        Log.i(TAG, "onMenuItemClick: More");
                        if (i % 2 == 0){

                            recyclerViewHorizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,300));
                        }else {
                            recyclerViewHorizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0));
                        }
                        i++;
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


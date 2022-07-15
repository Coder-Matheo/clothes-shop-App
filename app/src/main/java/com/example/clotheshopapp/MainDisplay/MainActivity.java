package com.example.clotheshopapp.MainDisplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clotheshopapp.MainDisplay.Account.AccountFragment;
import com.example.clotheshopapp.MainDisplay.Adminstrative.QueryGetValueAdmin;
import com.example.clotheshopapp.MainDisplay.Detail.DetailProductFragment;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonProduct;
import com.example.clotheshopapp.MainDisplay.login.LoggingActivity;
import com.example.clotheshopapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
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
    BottomNavigationView mainBottomNavigationView;

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
        setBottomNavigationView();

        //insertSingleProduct();
        QueryGetValueAdmin queryGetValueAdmin = new QueryGetValueAdmin(this);
        queryGetValueAdmin.getAllPro();



    }




    public void insertSingleProduct(){
        ProductData productData = new ProductData("Mattheow", "10000", "212");
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        insertAsyncTask.execute(productData);
    }




    class InsertAsyncTask extends AsyncTask<ProductData, Void, Void> {

        @Override
        protected Void doInBackground(ProductData... productData) {
            MySingletonProduct.getInstance(getApplicationContext())
                    .productDao()
                    .insertProduct(productData[0]);
            Log.i(TAG, "doInBackground: ");
            return null;
        }
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
                switch (item.getItemId()){
                    case R.id.topOffButton:
                        if (i % 2 == 0){
                            //Animate the RecyclerView Slide Up/Slide In
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

    private void setBottomNavigationView() {
        mainBottomNavigationView = findViewById(R.id.mainBottomNavigationView);
        mainBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeBottomNavItem:
                        Log.i(TAG, "onNavigationItemSelected: "+item.getItemId());
                        return true;
                    case R.id.noteBottomNavItem:
                        Log.i(TAG, "onNavigationItemSelected: "+item.getItemId());
                        return true;
                    case R.id.shopBottomNavItem:
                        Log.i(TAG, "onNavigationItemSelected: "+item.getItemId());
                        return true;
                    case R.id.accountBottomNavItem:
                        Intent intent = new Intent(getApplicationContext(), LoggingActivity.class);


                        startActivity(intent);
                        Log.i(TAG, "onNavigationItemSelected: "+item.getItemId());
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


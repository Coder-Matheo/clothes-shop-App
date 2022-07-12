package com.example.clotheshopapp.MainDisplay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.Retrofit1.RetrofitService1;
import com.example.clotheshopapp.MainDisplay.Retrofit1.ServerApi;
import com.example.clotheshopapp.R;

import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadServerTestActivity extends AppCompatActivity {
    private static final String TAG = "LoadServerTestActivity";

    RetrofitService1 retrofitService1 = new RetrofitService1();
    ServerApi serverApi = retrofitService1.getRetrofit().create(ServerApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_server_test);

       // initialComponent();
        //initialListComponent();
    }

    private void initialListComponent() {

        /*serverApi.getAllProduct()
                .enqueue(new Callback<List<ProductData>>() {
                    @Override
                    public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                        for (ProductData s : response.body()){
                            Log.i(TAG, "onResponse: "+s);
                        }


                    }

                    @Override
                    public void onFailure(Call<List<ProductData>> call, Throwable t) {

                    }
                });*/


    }

    private void initialComponent() {
        TextInputEditText nameEditText = findViewById(R.id.nameId);
        TextInputEditText branchEditText = findViewById(R.id.branchId);
        TextInputEditText locationEditText = findViewById(R.id.locationId);
        Button saveButton = findViewById(R.id.saveButton);





        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(nameEditText.getText());
                String price = branchEditText.getText().toString();
                String location = String.valueOf(locationEditText.getText());

                ProductData productData = new ProductData();
                productData.setProduct_dateOff(price);
                productData.setProductPrice(location);
                productData.setProductName(name);

                serverApi.save(productData).enqueue(new Callback<ProductData>() {
                    @Override
                    public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                        Toast.makeText(LoadServerTestActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ProductData> call, Throwable t) {
                        Toast.makeText(LoadServerTestActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(LoadServerTestActivity.class.getName()).log(Level.SEVERE,"Error occurred",t);
                    }
                });

            }
        });

    }
}
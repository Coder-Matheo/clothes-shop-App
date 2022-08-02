package com.example.clotheshopapp.MainDisplay.login;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataConverter;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataViewModel;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.SameFeature.AlertWindow;
import com.example.clotheshopapp.R;
import java.util.Locale;
import de.hdodenhof.circleimageview.CircleImageView;

public class OnClickCheckBox extends Fragment implements View.OnClickListener {
    private static final String TAG = "OnClickCheckBox";

    private Application application;
    private CircleImageView productImageCircleView;
    private Bitmap bitmap;
    private CheckBox maennlichCheckbox;
    private CheckBox weiblichCheckbox;
    private CheckBox kindlichCheckbox;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText dateOffEditText;
    private Button releaseButton;
    private DataViewModel dataViewModel;
    private AlertWindow alertWindow;

    public OnClickCheckBox(AdminModel adminModel) {
        this.application = adminModel.getApplication();
        this.productImageCircleView = adminModel.getProductImageCircleView();
        this.bitmap = adminModel.getBitmap();
        this.maennlichCheckbox = adminModel.getMaennlichCheckbox();
        this.weiblichCheckbox = adminModel.getWeiblichCheckbox();
        this.kindlichCheckbox = adminModel.getKindlichCheckbox();
        this.releaseButton = adminModel.getReleaseNewProductButton();
        this.productNameEditText = adminModel.getProductNameEditText();
        this.productPriceEditText = adminModel.getProductPriceEditText();
        this.dateOffEditText = adminModel.getDateOffEditText();
        this.dataViewModel = new DataViewModel(this.application);
        this.alertWindow = new AlertWindow(this.application);

        setAddImageProduct();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.maennlichCheckbox:
                Log.i(TAG, "onClick: maennlichCheckbox");
                this.kindlichCheckbox.setChecked(false);
                this.weiblichCheckbox.setChecked(false);
                setReleaseButton(this.maennlichCheckbox.getText().toString().trim().toUpperCase(Locale.ROOT));
                break;
            case R.id.weiblichCheckbox:
                Log.i(TAG, "onClick: weiblichCheckbox");
                this.kindlichCheckbox.setChecked(false);
                this.maennlichCheckbox.setChecked(false);
                setReleaseButton(this.weiblichCheckbox.getText().toString().trim().toUpperCase(Locale.ROOT));
                break;
            case R.id.kindlichCheckbox:
                Log.i(TAG, "onClick: kindlichCheckbox");
                this.maennlichCheckbox.setChecked(false);
                this.weiblichCheckbox.setChecked(false);
                setReleaseButton(this.kindlichCheckbox.getText().toString().trim().toUpperCase(Locale.ROOT));
                break;
        }

    }

    private void setAddImageProduct() {

        this.productImageCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ask to permission
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // ******** code for crop image
                i.putExtra("crop", "true");
                i.putExtra("aspectX", 100);
                i.putExtra("aspectY", 100);
                i.putExtra("outputX", 256);
                i.putExtra("outputY", 356);

                try {
                    i.putExtra("return-data", true);
                    startActivityForResult(
                            Intent.createChooser(i, "Select Picture"), 0);
                } catch (ActivityNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void setReleaseButton(String isChecked) {
        this.releaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(productPriceEditText.getText().toString().trim())
                        && !TextUtils.isEmpty(productNameEditText.getText().toString().trim())
                        && !TextUtils.isEmpty(dateOffEditText.getText().toString().trim())){
                    try {
                        DataConverter dataConverter = new DataConverter();
                        ProductData productData = new ProductData(String.valueOf(productNameEditText.getText()),
                                String.valueOf(productPriceEditText.getText()),String.valueOf(dateOffEditText.getText())
                                , dataConverter.convertImage2ByteArray(bitmap)
                        );

                        boolean isInserted = dataViewModel.insertProductQuery(productData);
                        if (isInserted == true){
                            alertWindow.toastAlert("New Product Posted",1);
                            //startActivity(new Intent(, MainActivity.class));
                        }else {
                            alertWindow.toastAlert("Error in New Product",1);

                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                Log.i(TAG, "onClick: " + isChecked);
                Log.i(TAG, "onClick:  releaseButton " + maennlichCheckbox.getText());
                Log.i(TAG, "onClick:  releaseButton " + productPriceEditText.getText());
                Log.i(TAG, "onClick: " + bitmap);
            }
        });
    }
}

package com.example.clotheshopapp.MainDisplay.login;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clotheshopapp.MainDisplay.Adminstrative.QueryAuthorizedUser;
import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataConverter;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataViewModel;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.SameFeature.AlertWindow;
import com.example.clotheshopapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminFragment extends Fragment {

    private CircleImageView productImageCircleView;
    private View view;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText dateOffEditText;
    private Button releaseNewProductButton;
    private Button existAccountSignUpButton;
    private static final String TAG = "AdminFragment";
    private DataViewModel dataViewModel;
    private AlertWindow alertWindow;
    private CheckBox maennlichCheckbox;
    private CheckBox weiblichCheckbox;
    private CheckBox kindlichCheckbox;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.admin_fragment, container, false);

        //dataViewModel = new DataViewModel(getActivity().getApplication());
        //alertWindow = new AlertWindow(getActivity().getApplicationContext());
        setElementUI(view);
        //decided System is Admin or normal User, then can to Upload new Product
        QueryAuthorizedUser queryAuthorizedUser = new QueryAuthorizedUser(getViewLifecycleOwner(), "Admin",getContext());
        //setAddImageSignUp();
        return view;
    }

    private void setElementUI(View view) {
        productImageCircleView = view.findViewById(R.id.signup_image_view);
        productNameEditText = view.findViewById(R.id.productNameEditText);
        productPriceEditText = view.findViewById(R.id.productPriceEditText);
        dateOffEditText = view.findViewById(R.id.dateOffEditText);
        releaseNewProductButton = view.findViewById(R.id.releaseNewProductButton);
        existAccountSignUpButton = view.findViewById(R.id.existAccountSignUpButton);

        maennlichCheckbox = view.findViewById(R.id.maennlichCheckbox);
        weiblichCheckbox = view.findViewById(R.id.weiblichCheckbox);
        kindlichCheckbox = view.findViewById(R.id.kindlichCheckbox);


        setAddImageSignUp();
        /*productNameEditText.addTextChangedListener(new GenericTextWatcher(productNameEditText, releaseNewProductButton, getViewLifecycleOwner(), getContext()));
        productPriceEditText.addTextChangedListener(new GenericTextWatcher(productPriceEditText, releaseNewProductButton, getViewLifecycleOwner(), getContext()));
        dateOffEditText.addTextChangedListener(new GenericTextWatcher(dateOffEditText, releaseNewProductButton, getViewLifecycleOwner(), getContext()));
        productNameEditText.addTextChangedListener(new GenericTextWatcher(dateOffEditText, existAccountSignUpButton, getViewLifecycleOwner(), getContext()));
        */

    }

    private void setAddImageSignUp() {

        productImageCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ask to permission
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
                }catch (ActivityNotFoundException ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            Uri uri = data.getData();

            InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


            final int maxSize = 768;
            int outWidth;
            int outHeight;
            int inWidth = bitmap.getWidth();
            int inHeight = bitmap.getHeight();
            if (inWidth > inHeight) {
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }

            bitmap = Bitmap.createScaledBitmap(bitmap, outWidth, outHeight,
                    false);

            productImageCircleView.setImageBitmap(bitmap);

            AdminModel adminModel = new AdminModel(getContext(),getActivity().getApplication(),productImageCircleView, bitmap, maennlichCheckbox, weiblichCheckbox, kindlichCheckbox, productNameEditText, productPriceEditText,
                    dateOffEditText, releaseNewProductButton);
            maennlichCheckbox.setOnClickListener(new OnClickCheckBox(adminModel));
            weiblichCheckbox.setOnClickListener(new OnClickCheckBox(adminModel));
            kindlichCheckbox.setOnClickListener(new OnClickCheckBox(adminModel ));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

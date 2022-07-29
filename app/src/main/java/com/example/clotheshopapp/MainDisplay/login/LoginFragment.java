package com.example.clotheshopapp.MainDisplay.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataConverter;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataViewModel;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginFragment extends Fragment {

    private EditText emailLogInEditText;
    private EditText passwordLogInEditText;
    private Button logInButton;
    private CheckBox adminCheckBox;
    private Button createAccountLogInButton;
    private CircleImageView profile_login_circleImageView;
    private DataViewModel dataViewModel;
    private static final String TAG = "LoginFragment";
    private TextWatcherHelper textWatcherHelper;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.log_in_fragment, container, false);
        emailLogInEditText = view.findViewById(R.id.emailLogInEditText);
        passwordLogInEditText = view.findViewById(R.id.passwordLogInEditText);
        logInButton = view.findViewById(R.id.logInButton);
        createAccountLogInButton = view.findViewById(R.id.createAccountLogInButton);
        profile_login_circleImageView = view.findViewById(R.id.profile_log_in_image_view);
        adminCheckBox = view.findViewById(R.id.admin_checkBox);
        //passwordLogInEditText.addTextChangedListener(new GenericTextWatcher(passwordLogInEditText, logInButton, getViewLifecycleOwner(),getContext()));
        //emailLogInEditText.addTextChangedListener(new GenericTextWatcher(emailLogInEditText, logInButton, getViewLifecycleOwner(),getContext()));

        dataViewModel = new DataViewModel(getActivity().getApplication());
        textWatcherHelper = new TextWatcherHelper(getContext(),getViewLifecycleOwner());


        verifyEmail(emailLogInEditText, passwordLogInEditText);

        return view;
    }

    private void verifyEmail(EditText emailLogInEditText, EditText passwordLogInEditText) {
        emailLogInEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = String.valueOf(editable).toUpperCase(Locale.ROOT);

                LiveData<List<UserDataObj>> get = dataViewModel.userFindByEmailQuery(email);
                get.observe(getViewLifecycleOwner(), new Observer<List<UserDataObj>>() {
                    @Override
                    public void onChanged(List<UserDataObj> userDataObjs) {

                        if (userDataObjs.size() != 0){
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frameLayout_logging, new AdminFragment());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }else {
                            setAddImageSignUp();
                        }
                    }
                });
            }
        });
    }



    private void setAddImageSignUp() {

        profile_login_circleImageView.setOnClickListener(new View.OnClickListener() {
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

            profile_login_circleImageView.setImageBitmap(bitmap);

            setNewUsers(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void setNewUsers(Bitmap bitmap) {
        DataConverter dataConverter = new DataConverter();
        Log.i(TAG, "setNewUsers: "+adminCheckBox.getText());
        try {
            if (textWatcherHelper.validationEmail(emailLogInEditText.getText().toString().toUpperCase(Locale.ROOT))){

                UserDataObj userDataObj = new UserDataObj("Admin",
                        emailLogInEditText.getText().toString().toUpperCase(Locale.ROOT),
                        passwordLogInEditText.getText().toString().toUpperCase(Locale.ROOT) ,
                        dataConverter.convertImage2ByteArray(bitmap));
                dataViewModel.insertUserQuery(userDataObj);
            }
        }catch (Exception e){
            Log.e(TAG, "Error or exists user");
        }
    }


}


package com.example.clotheshopapp.MainDisplay.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clotheshopapp.MainDisplay.Adminstrative.QueryAuthorizedUser;
import com.example.clotheshopapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminFragment extends Fragment {

    private CircleImageView signUpImageCircleView;
    private View view;
    private EditText emailSignUpEditText;
    private EditText passwordSignUpEditText;
    private EditText passwordRepeatSignUpEditText;
    private Button createAccountSignUpButton;
    private Button existAccountSignUpButton;
    private static final String TAG = "SignUpFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.admin_fragment, container, false);

        setElementUI(view);
        //decided System is Admin or normal User, then can to Upload new Product
        QueryAuthorizedUser queryAuthorizedUser = new QueryAuthorizedUser(getViewLifecycleOwner(), "Admin",getContext());
        setAddImageSignUp();
        return view;
    }

    private void setElementUI(View view) {
        signUpImageCircleView = view.findViewById(R.id.signup_image_view);
        emailSignUpEditText = view.findViewById(R.id.emailSignUpEditText);
        passwordSignUpEditText = view.findViewById(R.id.passwordSignUpEditText);
        passwordRepeatSignUpEditText = view.findViewById(R.id.passwordRepeatSignUpEditText);
        createAccountSignUpButton = view.findViewById(R.id.createAccountSignUpButton);
        existAccountSignUpButton = view.findViewById(R.id.existAccountSignUpButton);

        emailSignUpEditText.addTextChangedListener(new GenericTextWatcher(emailSignUpEditText, createAccountSignUpButton, getViewLifecycleOwner(), getContext()));
        passwordSignUpEditText.addTextChangedListener(new GenericTextWatcher(passwordSignUpEditText, createAccountSignUpButton, getViewLifecycleOwner(), getContext()));
        passwordRepeatSignUpEditText.addTextChangedListener(new GenericTextWatcher(passwordRepeatSignUpEditText, createAccountSignUpButton, getViewLifecycleOwner(), getContext()));
        emailSignUpEditText.addTextChangedListener(new GenericTextWatcher(passwordRepeatSignUpEditText, existAccountSignUpButton, getViewLifecycleOwner(), getContext()));

    }




    private void setEmailPasswordProcessElementUI() {
        String email = emailSignUpEditText.getText().toString().trim();
        String password = passwordSignUpEditText.getText().toString().trim();
        String repeatPassword = passwordRepeatSignUpEditText.getText().toString().trim();

        /*createAccountSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password == repeatPassword){
                    Log.i(TAG, "onClick: ");
                }else {
                    Log.i(TAG, "onClick: if Account Exist");
                    setJumpToExistAccount();
                }
            }
        });*/
    }


    private void setAddImageSignUp() {

        signUpImageCircleView.setOnClickListener(new View.OnClickListener() {
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

        Uri uri = data.getData();

        try {
            InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            signUpImageCircleView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

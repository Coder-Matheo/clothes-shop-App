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
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.clotheshopapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpFragment extends Fragment {

    CircleImageView signUpImageCircleView;
    View view;
    EditText emailSignUpEditText;
    EditText passwordSignUpEditText;
    EditText passwordRepeatSignUpEditText;
    Button createAccountSignUpButton;
    ImageButton existAccountSignUpButton;
    private static final String TAG = "SignUpFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        signUpImageCircleView = view.findViewById(R.id.signup_image_view);

        setElementUI(view);
        setAddImageSignUp();
        return view;
    }

    private void setElementUI(View view) {
        emailSignUpEditText = view.findViewById(R.id.emailSignUpEditText);
        passwordSignUpEditText = view.findViewById(R.id.passwordSignUpEditText);
        passwordRepeatSignUpEditText = view.findViewById(R.id.passwordRepeatSignUpEditText);
        createAccountSignUpButton = view.findViewById(R.id.createAccountSignUpButton);
        existAccountSignUpButton = view.findViewById(R.id.existAccountSignUpButton);

        emailSignUpEditText.addTextChangedListener(new GenericTextWatcher(emailSignUpEditText));
        passwordSignUpEditText.addTextChangedListener(new GenericTextWatcher(passwordRepeatSignUpEditText));
        passwordRepeatSignUpEditText.addTextChangedListener(new GenericTextWatcher(passwordRepeatSignUpEditText));


        setEmailValidation();



    }

    private void setEmailValidation() {


        emailSignUpEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                Pattern VALID_EMAIL_ADDRESS_REGEX =
                        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(charSequence);
                Log.i(TAG, "onTextChanged 69: "+matcher.find());
                boolean valid = matcher.find();
                if (valid){
                    setStrongPasswordValidation();
                    Log.i(TAG, "onTextChanged: Valid Email");
                    setEmailPasswordProcessElementUI();
                }if(valid == true) {
                    Log.i(TAG, "onTextChanged: UnValid Email");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setStrongPasswordValidation() {

        passwordSignUpEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
                Matcher matcher = pattern.matcher(charSequence);

                Log.i(TAG, "onTextChanged: "+matcher.matches());
                Log.i(TAG, "onTextChanged: "+charSequence);

                if (passwordSignUpEditText == passwordRepeatSignUpEditText){
                    Log.i(TAG, "onTextChanged: is equal");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void setEmailPasswordProcessElementUI() {
        String email = emailSignUpEditText.getText().toString().trim();
        String password = passwordSignUpEditText.getText().toString().trim();
        String repeatPassword = passwordRepeatSignUpEditText.getText().toString().trim();

        createAccountSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password == repeatPassword){
                    Log.i(TAG, "onClick: ");
                }else {
                    Log.i(TAG, "onClick: if Account Exist");
                    setJumpToExistAccount();
                }
            }
        });
    }

    private void setJumpToExistAccount() {
        Log.i(TAG, "setJumpExistAccount: ");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_logging, new LoginFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

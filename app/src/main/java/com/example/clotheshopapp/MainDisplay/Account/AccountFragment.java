package com.example.clotheshopapp.MainDisplay.Account;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.R;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class AccountFragment extends Fragment {

    Button profileBackButton;
    TextView emailAccountTextView;
    TextView passwordAccountTextView;
    TextView numberAccountTextView;
    ImageView imageAddImageView;
    ImageView profileImageView;


    private static final String TAG = "AccountFragment";
    final int REQUEST_CODE_GALLERY = 999;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        profileImageView = view.findViewById(R.id.profileImageView);

        setProfilBackButton(view);
        setAddImageProfil(view);

        setInitElement(view);
        return view;
    }



    private void setInitElement(View view) {
        passwordAccountTextView = view.findViewById(R.id.passwordAccountTextView);
        emailAccountTextView = view.findViewById(R.id.emailAccountTextView);
        numberAccountTextView = view.findViewById(R.id.numberAccountTextView);

        View.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.emailAccountTextView:
                        setEditableTextView(view, emailAccountTextView, "E-Mail");
                        break;
                    case R.id.passwordAccountTextView:
                        setEditableTextView(view, passwordAccountTextView, "Password");
                        break;
                    case R.id.numberAccountTextView:
                        setEditableTextView(view, numberAccountTextView, "Phone Number");
                        break;
                }
            }
        };

        passwordAccountTextView.setOnClickListener(handler);
        emailAccountTextView.setOnClickListener(handler);
        numberAccountTextView.setOnClickListener(handler);
    }


    private void setProfilBackButton(View view) {
        profileBackButton = view.findViewById(R.id.profilBackButton);
        profileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent, options.toBundle());

            }
        });
    }

    private void setEditableTextView(View view, TextView fieldTextView, String titleDialog) {

        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        EditText emailEditText = new EditText(getContext());
        emailEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        dialog.setTitle("Edit " + titleDialog);
        dialog.setView(emailEditText);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fieldTextView.setText(emailEditText.getText());
            }
        });

        fieldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailEditText.setText(fieldTextView.getText());
                dialog.show();
            }
        });
    }

    private void setAddImageProfil(View view) {

        profileImageView = view.findViewById(R.id.profileImageView);
        profileImageView.setOnClickListener(new View.OnClickListener() {
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
            profileImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
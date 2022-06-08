package com.example.clotheshopapp.MainDisplay.Account;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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

import com.example.clotheshopapp.MainDisplay.MainActivity;
import com.example.clotheshopapp.R;


public class AccountFragment extends Fragment {

    Button profileBackButton;
    TextView emailAccountTextView;
    TextView passwordAccountTextView;
    TextView numberAccountTextView;
    ImageView imageAddImageView;
    private static final String TAG = "AccountFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        setProfilBackButton(view);
        setAddImageProfil(view);

        setInitElement(view);
        return view;
    }

    private void setAddImageProfil(View view) {

        imageAddImageView = view.findViewById(R.id.imageAddImageView);
        imageAddImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(TAG, "onClick: ");
            }
        });
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

    private void setEditableTextView(View view, TextView emailAccountTextView, String titleDialog) {

        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        EditText emailEditText = new EditText(getContext());
        emailEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        dialog.setTitle("Edit " + titleDialog);
        dialog.setView(emailEditText);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                emailAccountTextView.setText(emailEditText.getText());
            }
        });

        emailAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailEditText.setText(emailAccountTextView.getText());
                dialog.show();
            }
        });
    }



}
package com.example.clotheshopapp.MainDisplay.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataViewModel;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;
import com.example.clotheshopapp.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {

    private EditText emailLogInEditText;
    private EditText passwordLogInEditText;
    private Button logInButton;
    private Button createAccountLogInButton;
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
                            try {
                               if (textWatcherHelper.validationEmail(emailLogInEditText.getText().toString().toUpperCase(Locale.ROOT))){
                                   Log.i(TAG, "password: "+ passwordLogInEditText.getText().toString());
                                   UserDataObj userDataObj = new UserDataObj("New User Test",
                                           emailLogInEditText.getText().toString().toUpperCase(Locale.ROOT),
                                           "1234asdf" ,"BILD");
                                   dataViewModel.insertUserQuery(userDataObj);
                                }
                            }catch (Exception e){
                                Log.e(TAG, "Error or exists user");
                            }
                        }
                    }
                });

            }
        });
    }


}


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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.clotheshopapp.MainDisplay.RoomDatabase.DataViewModel;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.ProductData;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Model.UserDataObj;
import com.example.clotheshopapp.MainDisplay.RoomDatabase.Singleton.MySingletonUser;
import com.example.clotheshopapp.R;

import java.util.List;

public class LoginFragment extends Fragment {

    EditText emailLogInEditText;
    EditText passwordLogInEditText;
    Button logInButton;
    Button createAccountLogInButton;
    DataViewModel dataViewModel;
    private static final String TAG = "LoginFragment";
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.log_in_fragment, container, false);
        emailLogInEditText = view.findViewById(R.id.emailLogInEditText);
        passwordLogInEditText = view.findViewById(R.id.passwordLogInEditText);
        logInButton = view.findViewById(R.id.logInButton);
        createAccountLogInButton = view.findViewById(R.id.createAccountLogInButton);

        passwordLogInEditText.addTextChangedListener(new GenericTextWatcher(passwordLogInEditText, logInButton, getViewLifecycleOwner(),getContext()));
        emailLogInEditText.addTextChangedListener(new GenericTextWatcher(emailLogInEditText, logInButton, getViewLifecycleOwner(),getContext()));

        dataViewModel = new DataViewModel(getActivity().getApplication());



        UserDataObj userDataObj = new UserDataObj("New User Test","ALI.DINARVAND@GMAIL.COM","1234asdf","BILD");
        dataViewModel.insertUserQuery(userDataObj);

        /*LiveData<List<UserDataObj>> get1 = dataViewModel.getAllUser();
        get1.observe(getViewLifecycleOwner(), new Observer<List<UserDataObj>>() {
            @Override
            public void onChanged(List<UserDataObj> userDataObjs) {
                Log.i(TAG, "onChanged: "+userDataObjs);
            }
        });*/



        return view;
    }

    private void verifyEmail(EditText emailLogInEditText) {
        emailLogInEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = String.valueOf(editable);
                LiveData<List<UserDataObj>> get = dataViewModel.userFindByEmailQuery(email);
                get.observe(getViewLifecycleOwner(), new Observer<List<UserDataObj>>() {
                    @Override
                    public void onChanged(List<UserDataObj> userDataObjs) {
                        Log.i(TAG, "onChanged: "+userDataObjs.size());
                        Log.i(TAG, "onChanged: "+userDataObjs);
                    }
                });




                LiveData<List<UserDataObj>> getUserName = MySingletonUser.getInstance(view.getContext())
                        .userDao()
                        .userFindByEmail(email);

                getUserName.observe(getViewLifecycleOwner(), new Observer<List<UserDataObj>>() {
                    @Override
                    public void onChanged(List<UserDataObj> userDataObjs) {
                        try{

                            Log.i(TAG, "You are Admin, you can access Data");
                            Log.i(TAG, "user449: "+userDataObjs);

                    /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout_logging, new LoginFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();*/

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


}


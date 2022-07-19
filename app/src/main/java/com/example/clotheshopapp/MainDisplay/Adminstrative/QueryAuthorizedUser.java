package com.example.clotheshopapp.MainDisplay.Adminstrative;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.example.clotheshopapp.MainDisplay.MainActivity;

public class QueryAuthorizedUser extends Fragment {
    private static final String TAG = "QueryGetAllUser";
    private LifecycleOwner lifecycleOwner;
    private Context context;

    public QueryAuthorizedUser(LifecycleOwner lifecycleOwner, String currentUser, Context context) {
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        System.out.println("*** Modified Proxy Pattern Demo *** \n");
        //Admin is an authorized user
        ModifiedProxy px1 = new ModifiedProxy(currentUser);
        px1.doSomeWork(lifecycleOwner, this.context);
        //Robin is an unauthorized user
        //ModifiedProxy px2 = new ModifiedProxy("Robin");
        //px2.doSomeWork(mainActivity);
    }


}




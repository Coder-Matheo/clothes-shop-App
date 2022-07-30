package com.example.clotheshopapp.MainDisplay.SameFeature;

import android.content.Context;
import android.widget.Toast;

public class AlertWindow {
    private Context context;

    public AlertWindow(Context context) {
        this.context = context;
    }

    public void toastAlert(String textToast, int durAlert){
        Toast toast = new Toast(this.context);
        toast.setText(textToast);
        if (durAlert == 1){
            toast.setDuration(Toast.LENGTH_SHORT);
        }else {
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }
}

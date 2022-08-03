package com.example.clotheshopapp.MainDisplay.Detail;

import android.widget.ImageView;
import android.widget.TextView;

public interface OnClickInterfaceAdapter {
    void onClickListenerInterface(int position, TextView descriptionProduct, TextView priceOfProduct, ImageView productImageView);
}

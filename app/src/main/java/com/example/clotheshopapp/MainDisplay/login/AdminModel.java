package com.example.clotheshopapp.MainDisplay.login;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminModel {
    private Context applicationContext;
    private Application application;
    private CircleImageView productImageCircleView;
    private Bitmap bitmap;
    private CheckBox maennlichCheckbox;
    private CheckBox weiblichCheckbox;
    private CheckBox kindlichCheckbox;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText dateOffEditText;
    private Button releaseNewProductButton;


    public AdminModel(Context applicationContext, Application application, CircleImageView productImageCircleView, Bitmap bitmap, CheckBox maennlichCheckbox, CheckBox weiblichCheckbox, CheckBox kindlichCheckbox, EditText productNameEditText, EditText productPriceEditText, EditText dateOffEditText, Button releaseNewProductButton) {
        this.applicationContext = applicationContext;
        this.application = application;
        this.productImageCircleView = productImageCircleView;
        this.bitmap = bitmap;
        this.maennlichCheckbox = maennlichCheckbox;
        this.weiblichCheckbox = weiblichCheckbox;
        this.kindlichCheckbox = kindlichCheckbox;
        this.productNameEditText = productNameEditText;
        this.productPriceEditText = productPriceEditText;
        this.dateOffEditText = dateOffEditText;
        this.releaseNewProductButton = releaseNewProductButton;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public CircleImageView getProductImageCircleView() {
        return productImageCircleView;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setProductImageCircleView(CircleImageView productImageCircleView) {
        this.productImageCircleView = productImageCircleView;
    }

    public CheckBox getMaennlichCheckbox() {
        return maennlichCheckbox;
    }

    public void setMaennlichCheckbox(CheckBox maennlichCheckbox) {
        this.maennlichCheckbox = maennlichCheckbox;
    }

    public CheckBox getWeiblichCheckbox() {
        return weiblichCheckbox;
    }

    public void setWeiblichCheckbox(CheckBox weiblichCheckbox) {
        this.weiblichCheckbox = weiblichCheckbox;
    }

    public CheckBox getKindlichCheckbox() {
        return kindlichCheckbox;
    }

    public void setKindlichCheckbox(CheckBox kindlichCheckbox) {
        this.kindlichCheckbox = kindlichCheckbox;
    }

    public EditText getProductNameEditText() {
        return productNameEditText;
    }

    public void setProductNameEditText(EditText productNameEditText) {
        this.productNameEditText = productNameEditText;
    }

    public EditText getProductPriceEditText() {
        return productPriceEditText;
    }

    public void setProductPriceEditText(EditText productPriceEditText) {
        this.productPriceEditText = productPriceEditText;
    }

    public EditText getDateOffEditText() {
        return dateOffEditText;
    }

    public void setDateOffEditText(EditText dateOffEditText) {
        this.dateOffEditText = dateOffEditText;
    }

    public Button getReleaseNewProductButton() {
        return releaseNewProductButton;
    }

    public void setReleaseNewProductButton(Button releaseNewProductButton) {
        this.releaseNewProductButton = releaseNewProductButton;
    }
}

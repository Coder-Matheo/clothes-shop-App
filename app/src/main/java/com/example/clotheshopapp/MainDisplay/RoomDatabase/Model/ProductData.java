package com.example.clotheshopapp.MainDisplay.RoomDatabase.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity
public class ProductData {

    @PrimaryKey(autoGenerate = true)
    private int bid;
    @ColumnInfo(name = "product_name")
    private String productName;
    @ColumnInfo(name = "product_price")
    private String productPrice;
    @ColumnInfo(name = "data_off")
    private String product_dateOff;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "product_image")
    private byte[] product_image;

    public ProductData(String productName, String productPrice, String product_dateOff) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.product_dateOff = product_dateOff;
    }

    public ProductData() {

    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProduct_dateOff() {
        return product_dateOff;
    }

    public void setProduct_dateOff(String product_dateOff) {
        this.product_dateOff = product_dateOff;
    }

    public byte[] getProduct_image() {
        return product_image;
    }

    public void setProduct_image(byte[] product_image) {
        this.product_image = product_image;
    }


    @Override
    public String toString() {
        return "ProductData{" +
                "Id=" + bid +
                ", proName='" + productName + '\'' +
                ", proPrice='" + productPrice + '\'' +
                ", dateOff=" + product_dateOff +
                ", imgProduct=" + Arrays.toString(product_image) +
                '}';
    }
}

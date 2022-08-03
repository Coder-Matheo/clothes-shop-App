package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;

public class DataConverter {


    public  byte[]  convertImage2ByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public Bitmap convertByteArray2Image(byte[] array){
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }

    public Uri convertImage2UriPath(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes  = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }
}

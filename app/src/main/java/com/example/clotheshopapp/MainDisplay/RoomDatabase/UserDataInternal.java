package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserDataInternal {
    @PrimaryKey(autoGenerate = true)
    private int bid;

    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "bio_profile")
    private String profilePhoto;
    @ColumnInfo(name = "loc_profile")
    private String locProfile;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "img_profile")
    private byte[] imgProfile;
    
    @Ignore
    public UserDataInternal(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public UserDataInternal(String userName, String phoneNumber, String password) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    /*public BioObj(String phoneNumber, String bioProfile, String locProfile, String joinProfile, byte[] imgProfile) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.bioProfile = bioProfile;
        this.locProfile = locProfile;
        this.joinProfile = joinProfile;
        this.imgProfile = imgProfile;
    }*/

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getLocProfile() {
        return locProfile;
    }

    public void setLocProfile(String locProfile) {
        this.locProfile = locProfile;
    }

    public byte[] getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(byte[] imgProfile) {
        this.imgProfile = imgProfile;
    }



}

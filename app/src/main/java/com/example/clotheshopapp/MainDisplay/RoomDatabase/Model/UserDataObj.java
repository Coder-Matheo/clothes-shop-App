package com.example.clotheshopapp.MainDisplay.RoomDatabase.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity
public class UserDataObj {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userName;

    private String email;
    private String password;
    private String location;
    private String profilePhoto;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] imgProfile;
    private int bankAccountNumber;
    private int backAccountPassword;

    public UserDataObj() {
    }

    public UserDataObj(String userName, String email, String password, String profilePhoto) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public byte[] getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(byte[] imgProfile) {
        this.imgProfile = imgProfile;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getBackAccountPassword() {
        return backAccountPassword;
    }

    public void setBackAccountPassword(int backAccountPassword) {
        this.backAccountPassword = backAccountPassword;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", imgProfile=" + Arrays.toString(imgProfile) +
                ", bankAccountNumber=" + bankAccountNumber +
                ", backAccountPassword=" + backAccountPassword +
                '}';
    }
}

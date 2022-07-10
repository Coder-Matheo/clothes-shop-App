package com.example.clotheshopapp.MainDisplay.RoomDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;



@Entity
public class ProductDataInternal {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "bio_Id")
    private int bioId;
    @ColumnInfo(name = "product_name")
    private String productName;
    @ColumnInfo(name = "product_price")
    private String productPrice;
    @ColumnInfo(name = "message_post")
    private String messagePost;
    @ColumnInfo(name = "comment_post")
    private String commentPost;
    @ColumnInfo(name = "data_time")
    private String dataTime;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @ColumnInfo(name = "comment")
    private String comment;
    @ColumnInfo(name = "like")
    private Integer like;




    public ProductDataInternal(Integer like) {
        this.like = like;
    }

    @Ignore
    public ProductDataInternal(int bioId, String messagePost, Integer like) {
        this.bioId = bioId;
        this.messagePost = messagePost;
        this.like = like;
    }


    /*public PostObj(int bioId, String filmName, String filmTitle, String messagePost, String commentPost, String dataTime, byte[] image) {
        this.bioId = bioId;
        this.filmName = filmName;
        this.filmTitle = filmTitle;
        this.messagePost = messagePost;
        this.commentPost = commentPost;
        this.dataTime = dataTime;
        this.image = image;
    }*/


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBioId() {
        return bioId;
    }

    public void setBioId(int bioId) {
        this.bioId = bioId;
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

    public String getMessagePost() {
        return messagePost;
    }

    public void setMessagePost(String messagePost) {
        this.messagePost = messagePost;
    }

    public String getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(String commentPost) {
        this.commentPost = commentPost;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }


}

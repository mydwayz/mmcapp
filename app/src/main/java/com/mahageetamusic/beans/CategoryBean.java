package com.mahageetamusic.beans;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kishore on 9/12/2015.
 */
public class CategoryBean implements Parcelable {

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private int categoryID;
    private String categoryName,description,image;

    private CategoryBean(Parcel source) {
        this.categoryName = source.readString();
        this.categoryID = source.readInt();
        this.image = source.readString();
        this.description = source.readString();
    }

    public CategoryBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeInt(categoryID);
        dest.writeString(categoryName);
        dest.writeString(image);
        dest.writeString(description);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public CategoryBean createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new CategoryBean(source);
        }

        @Override
        public CategoryBean[] newArray(int size) {
            // TODO Auto-generated method stub
            return new CategoryBean[size];
        }
    };

}

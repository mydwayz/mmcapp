package com.mahageetamusic.beans;

import android.widget.CheckBox;

/**
 * Created by Kishore on 9/2/2015.
 */
public class NavDrawerItem {
    private String title;
    private boolean isChecked=false;
    private int icon;

    public NavDrawerItem(String title, int icon, boolean b){
        this.title = title;
        this.icon = icon;
        isChecked = b;
    }



    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

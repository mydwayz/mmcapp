package com.mahageetamusic.beans;

import android.support.annotation.Nullable;

import com.mahageetamusic.network.ResponseListener;

/**
 * Created by Kishore on 9/13/2015.
 */
public class ResponseObject {

    String mResponseStatus;
    @Nullable
    Object mResponse=null;

    @Nullable
    public Object getmResponse() {
        return mResponse;
    }

    public void setmResponse(@Nullable Object mResponse) {
        this.mResponse = mResponse;
    }

    public String getmResponseStatus() {
        return mResponseStatus;
    }

    public void setmResponseStatus(String mResponseStatus) {
        this.mResponseStatus = mResponseStatus;
    }

    public ResponseObject( String mResponseStatus,Object mResponse) {
        this.mResponse = mResponse;
        this.mResponseStatus = mResponseStatus;
    }

    public ResponseObject(String mResponseStatus) {
        this.mResponseStatus = mResponseStatus;
    }

    private ResponseObject(){}
}

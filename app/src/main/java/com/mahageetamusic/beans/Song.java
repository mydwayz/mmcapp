
package com.mahageetamusic.beans;

import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("songid")
    public String songid;
    @SerializedName("albumid")
    public String albumid;
    @SerializedName("songname")
    public String songname;
    @SerializedName("fullsong")
    public String fullsong;
    @SerializedName("partsong")
    public Object partsong;
    @SerializedName("ringtone")
    public Object ringtone;
    @SerializedName("non_india_price")
    public String nonIndiaPrice;
    @SerializedName("india_price")
    public String indiaPrice;
    @SerializedName("top_album")
    public String topAlbum;

}

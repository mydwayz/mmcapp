
package com.mahageetamusic.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SongsAndRingtones implements Serializable{

    @SerializedName("album_songs")
    public AlbumSongs albumSongs;

}

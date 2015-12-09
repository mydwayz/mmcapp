
package com.mahageetamusic.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Results {

    @SerializedName("songs")
    public List<Song> songs = new ArrayList<Song>();
    @SerializedName("providers")
    public List<Provider> providers = new ArrayList<Provider>();
    @SerializedName("ringtones")
    public List<Ringtone> ringtones = new ArrayList<Ringtone>();

}

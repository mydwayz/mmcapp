package com.mahageetamusic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mahageetamusic.R;
import com.mahageetamusic.beans.SongsAndRingtones;

/**
 * Created by Kishore on 12/8/2015.
 */
public class AlbumRTAdapter extends RecyclerView.Adapter<AlbumRTAdapter.RTHolder> {
    private Context mContext;
    private SongsAndRingtones songs;
    public AlbumRTAdapter(Context context, SongsAndRingtones mParam2) {
        this.mContext = context;
        this.songs = mParam2;
    }

    @Override
    public RTHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RTHolder viewHolder = null;
        View view=null;
        if(view==null){
            view = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_ablumview_layout,parent);
            viewHolder = new RTHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RTHolder holder, int position) {
        holder.title.setText(songs.albumSongs.results.songs.get(position).songname);
        holder.currency.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return songs.albumSongs.results.ringtones.size();
    }


    public class RTHolder extends RecyclerView.ViewHolder{
        TextView title,currency;
        CheckBox add2Cart,play;
        public RTHolder(View itemView) {

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            currency = (TextView) itemView.findViewById(R.id.currency);
            add2Cart = (CheckBox) itemView.findViewById(R.id.add2cart);
            play = (CheckBox) itemView.findViewById(R.id.player);
        }
    }
}

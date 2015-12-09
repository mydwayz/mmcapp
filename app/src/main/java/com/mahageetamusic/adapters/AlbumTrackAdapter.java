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
public class AlbumTrackAdapter extends RecyclerView.Adapter<AlbumTrackAdapter.ViewHolder> {
    private Context mContext;
    private SongsAndRingtones songs;
    public AlbumTrackAdapter(Context context, SongsAndRingtones mParam2) {
        this.mContext = context;
        this.songs = mParam2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view=null;
        if(view==null){
            view = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_ablumview_layout,parent);
            viewHolder = new ViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(songs.albumSongs.results.songs.get(position).songname);
        holder.currency.setText(String.format(mContext.getResources().getString(R.string.rupees),""+songs.albumSongs.results.songs.get(position).indiaPrice));
    }

    @Override
    public int getItemCount() {
        return songs.albumSongs.results.songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,currency;
        CheckBox add2Cart,play;
        public ViewHolder(View itemView) {

            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            currency = (TextView) itemView.findViewById(R.id.currency);
            add2Cart = (CheckBox) itemView.findViewById(R.id.add2cart);
            play = (CheckBox) itemView.findViewById(R.id.player);
        }
    }
}

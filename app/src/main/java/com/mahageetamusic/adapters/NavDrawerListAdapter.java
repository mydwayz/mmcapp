package com.mahageetamusic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahageetamusic.R;
import com.mahageetamusic.beans.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by Kishore on 9/3/2015.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private ViewHolder viewHolder;
    public NavDrawerListAdapter(Context mContext, ArrayList<NavDrawerItem> navDrawerItems) {
        this.mContext = mContext;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_list_item,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(navDrawerItems.get(i).getIcon());
        viewHolder.imageView.setSelected(navDrawerItems.get(i).isChecked());
        viewHolder.textView.setText(navDrawerItems.get(i).getTitle());
        viewHolder.checkBox.setChecked(navDrawerItems.get(i).isChecked());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
        CheckBox checkBox;
        public ViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.icon);
            textView = (TextView) view.findViewById(R.id.title);
            checkBox = (CheckBox) view.findViewById(R.id.item_checkbox);
        }
    }

}

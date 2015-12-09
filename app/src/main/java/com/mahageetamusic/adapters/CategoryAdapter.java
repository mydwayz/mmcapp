package com.mahageetamusic.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahageetamusic.R;
import com.mahageetamusic.beans.CategoryBean;
import com.mahageetamusic.classes.HomeActivity;
import com.mahageetamusic.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kishore on 9/12/2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    int lastPosition=-1;
    Context mContext;
    ArrayList<CategoryBean> params;
    public CategoryAdapter(Context mCtx, List<CategoryBean> mParam2){
        mContext = mCtx;
        params = (ArrayList<CategoryBean>) mParam2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        ViewHolder viewHolder = new ViewHolder(((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.drawer_list_item,null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.v(" "+i,params.get(i).getCategoryName());
        viewHolder.position = i;
        viewHolder.textView.setText(params.get(i).getCategoryName());
        Picasso.with(mContext).load(Utils.CATEGORY_IMAGEPATH+params.get(i).getImage()).into(viewHolder.imageView);
        // Here you apply the animation when the view is bound
        setAnimation(viewHolder.cardview, i);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return params.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        RelativeLayout relativeLayout;
        TextView textView;
        CheckBox checkBox;
        int position;
        CardView cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            imageView = (ImageView)itemView.findViewById(R.id.icon);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.layout);
            textView = (TextView)itemView.findViewById(R.id.title);
            checkBox = (CheckBox)itemView.findViewById(R.id.item_checkbox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((HomeActivity)mContext).onFragmentInteraction("Category",params.get(position));
                }
            });
        }
    }
}

package com.mahageetamusic.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mahageetamusic.R;
import com.mahageetamusic.adapters.PagerAdapter;
import com.mahageetamusic.beans.SongsAndRingtones;

/**
 * Created by Kishore on 12/5/2015.
 */
public class AlbumFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private SongsAndRingtones mParam2;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    public static AlbumFragment newInstance(String param1, SongsAndRingtones param2) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = (SongsAndRingtones)getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"Album View",Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.activity_album_view,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setUpViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setUpViewPager(ViewPager viewpager) {
        adapter = new PagerAdapter(getChildFragmentManager());
        if(mParam2.albumSongs.results.songs.size()>0){
            adapter.addFragment(TrackFragment.newInstance("Tracks",mParam2),"Tracks");
        }
        if(mParam2.albumSongs.results.ringtones.size()>0){
            adapter.addFragment(RingtoneFragment.newInstance("Ringtones",mParam2),"Ringtones");
        }
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment item = adapter.getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

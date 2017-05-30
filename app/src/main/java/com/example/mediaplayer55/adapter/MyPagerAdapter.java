package com.example.mediaplayer55.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediaplayer55.MainActivity;
import com.example.mediaplayer55.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {


    private final ArrayList<BaseFragment> fragmens;

    //构造方法
    public MyPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragmens = fragments;
    }




    //返回对应的frament
    @Override
    public Fragment getItem(int position) {
        return fragmens.get(position);
    }


    //返回总条数
    @Override
    public int getCount() {
        return fragmens.size();
    }
}

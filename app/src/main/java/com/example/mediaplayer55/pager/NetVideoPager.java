package com.example.mediaplayer55.pager;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.mediaplayer55.fragment.BaseFragment;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class NetVideoPager extends BaseFragment {
    TextView textView;
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("这是网络视频界面");
        super.initData();
    }
}

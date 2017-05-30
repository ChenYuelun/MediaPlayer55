package com.example.mediaplayer55;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.mediaplayer55.adapter.MyPagerAdapter;
import com.example.mediaplayer55.fragment.BaseFragment;
import com.example.mediaplayer55.pager.LocalAudioPager;
import com.example.mediaplayer55.pager.LocalVideoPager;
import com.example.mediaplayer55.pager.NetAudioPager;
import com.example.mediaplayer55.pager.NetVideoPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private RadioGroup rg_tab;
    private ArrayList<BaseFragment> fragments;
    private MyPagerAdapter myAdapter;
    private int position;
    private int[] rbids = {R.id.rb_local_video,R.id.rb_local_audio,R.id.rb_net_video,R.id.rb_net_audio};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        initPager();

        rg_tab.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        rg_tab.check(R.id.rb_local_video);
    }

    private void initPager() {
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoPager());
        fragments.add(new LocalAudioPager());
        fragments.add(new NetVideoPager());
        fragments.add(new NetAudioPager());
        if (fragments != null && fragments.size() > 0) {
            FragmentManager manager = getSupportFragmentManager();
            myAdapter = new MyPagerAdapter(manager, fragments);
            viewpager.setAdapter(myAdapter);
            viewpager.setCurrentItem(0);
        }

    }





    //设置RadioButton的选中事件
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_local_video:
                    position = 0;
                    break;
                case R.id.rb_local_audio:
                    position = 1;
                    break;
                case R.id.rb_net_video:
                    position = 2;
                    break;
                case R.id.rb_net_audio:
                    position = 3;
                    break;
            }
            changePager();
        }
    }

    //选中不同的RadioButton时切换页面
    private void changePager() {
        viewpager.setCurrentItem(position);
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动的时候回调
         * @param position 当前滚动的页面的下标
         * @param positionOffset 当前页滑动的百分比
         * @param positionOffsetPixels 当前页面滑动了多少像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        /**
         * 当选中某个页面的时候回调
         * @param position 当前选中页面的下标位置
         */
        @Override
        public void onPageSelected(int position) {
            rg_tab.check(rbids[position]);
        }

        /**
         * 当页面状态变化的时候回调
         * ViewPager.SCROLL_STATE_IDLE; 空闲状态
         * ViewPager.SCROLL_STATE_DRAGGING 拖拽状态
         * ViewPager.SCROLL_STATE_SETTLING 惯性状态
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}

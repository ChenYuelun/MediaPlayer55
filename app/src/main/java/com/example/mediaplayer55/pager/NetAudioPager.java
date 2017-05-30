package com.example.mediaplayer55.pager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mediaplayer55.R;
import com.example.mediaplayer55.adapter.MyRecyclerViewAdapter;
import com.example.mediaplayer55.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class NetAudioPager extends BaseFragment {
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> datas;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.net_audio_recycler,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
          datas.add(i+"aaaaa"+i);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new MyRecyclerViewAdapter(context,datas);
        recyclerView.setAdapter(recyclerViewAdapter);

    }


}

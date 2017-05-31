package com.example.mediaplayer55.pager;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mediaplayer55.R;
import com.example.mediaplayer55.adapter.LocalAudioAdapter;
import com.example.mediaplayer55.domian.LocalMediaBean;
import com.example.mediaplayer55.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class LocalAudioPager extends BaseFragment {
    @Bind(R.id.local_audio_pager)
    RecyclerView localAudiopager;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;
    public static final int DATA_IS_READY = 2;
    private LocalAudioAdapter localAudioAdapter;
    private ArrayList<LocalMediaBean> datas;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == DATA_IS_READY) {
                if(datas != null && datas.size()>0) {
                    tvNomedia.setVisibility(View.GONE);
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                    localAudiopager.setLayoutManager(layoutManager);
                    localAudioAdapter = new LocalAudioAdapter(context,datas);

                    localAudiopager.setAdapter(localAudioAdapter);
                }else {
                    tvNomedia.setVisibility(View.VISIBLE);
                }
            }
        }
    };
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.local_audio_pager, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getLocalAudioData();
    }

    private void getLocalAudioData() {
        new Thread(){


            public void run(){
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATA,

                };
                Cursor cursor = resolver.query(uri, objs, null, null, null);
                if(cursor!= null) {
                    datas = new ArrayList<LocalMediaBean>();
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                        long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                        datas.add(new LocalMediaBean(name,duration,size,data));

                    }

                    cursor.close();
                    handler.sendEmptyMessage(DATA_IS_READY);

                }
            }
        }.start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}

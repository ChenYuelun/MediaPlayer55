package com.example.mediaplayer55.pager;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mediaplayer55.R;
import com.example.mediaplayer55.adapter.LocalVideoAdapter;
import com.example.mediaplayer55.domian.LocalMediaBean;
import com.example.mediaplayer55.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class LocalVideoPager extends BaseFragment {
    private static final int DATA_IS_REDAY = 1;
    @Bind(R.id.local_video_)
    ListView localVideo;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;
    private ArrayList<LocalMediaBean> datas;
    private LocalVideoAdapter localMediaAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == DATA_IS_REDAY) {
                if(datas!= null && datas.size()>0) {
                    tvNomedia.setVisibility(View.GONE);
                    localMediaAdapter = new LocalVideoAdapter(context, datas);
                    localVideo.setAdapter(localMediaAdapter);
                }else {
                    tvNomedia.setVisibility(View.VISIBLE);
                }

            }
        }
    };

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.local_video_pager, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getLocalVideoData();
    }

    private void getLocalVideoData() {
        new Thread() {
            public void run() {
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objes = {
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATA,

                };
                Cursor cursor = resolver.query(uri, objes, null, null, null, null);
                if (cursor != null) {
                    datas = new ArrayList<LocalMediaBean>();
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                        long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                        Log.e("TAG", "name:" + name + ",duration:" + duration + ",size:" + size + ",data:" + data);
                        datas.add(new LocalMediaBean(name, duration, size, data));
                    }
                    cursor.close();
                    handler.sendEmptyMessage(DATA_IS_REDAY);
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

package com.example.mediaplayer55.pager;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mediaplayer55.R;
import com.example.mediaplayer55.activity.MyItemDecoration;
import com.example.mediaplayer55.adapter.MyRecyclerViewAdapter;
import com.example.mediaplayer55.domian.NetAudioBean;
import com.example.mediaplayer55.fragment.BaseFragment;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class NetAudioPager extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;
    private String url = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
    private List<NetAudioBean.ListBean> list;
    private MyRecyclerViewAdapter myAdapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.net_audio_recycler, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFormNet();
    }

    private void getDataFormNet() {
        final RequestParams requst = new RequestParams(url);

        org.xutils.x.http().get(requst, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "Network request success,result:" + result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "Network request failure,Throwable:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String json) {
        NetAudioBean netAudioItem = new Gson().fromJson(json, NetAudioBean.class);
        list = netAudioItem.getList();
        Log.e("TAG", "数据已解析");
        if(list != null && list.size() > 0) {
            Log.e("TAG","数据合法");
            tvNomedia.setVisibility(View.GONE);
            Log.e("TAG","数据传入适配器");
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            Log.e("TAG","设置界面管理");
            recyclerview.setLayoutManager(layoutManager);
            myAdapter = new MyRecyclerViewAdapter(context,list);
            Log.e("TAG","设置适配器");
            recyclerview.setAdapter(myAdapter);
            recyclerview.addItemDecoration(new MyItemDecoration(context, MyItemDecoration.VERTICAL_LIST));
        }else {
            tvNomedia.setVisibility(View.VISIBLE);
        }
        tvNomedia.setVisibility(View.GONE);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}

package com.example.mediaplayer55.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediaplayer55.R;
import com.example.mediaplayer55.domian.LocalMediaBean;
import com.example.mediaplayer55.utils.Utils;

import java.util.ArrayList;

/**
 * Created by chenyuelun on 2017/5/31.
 */

public class LocalAudioAdapter extends RecyclerView.Adapter<LocalAudioAdapter.LocalAudioHolder> {
    private final ArrayList<LocalMediaBean> datas;
    private final Context context;
    private Utils utils;

    public LocalAudioAdapter(Context context, ArrayList<LocalMediaBean> datas) {
        this.context = context;
        this.datas = datas;
        utils = new Utils();
    }

    @Override
    public LocalAudioAdapter.LocalAudioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_meida_item, parent, false);
        return new LocalAudioHolder(view);
    }

    @Override
    public void onBindViewHolder(LocalAudioAdapter.LocalAudioHolder holder, int position) {
        LocalMediaBean mediaBean = datas.get(position);
        holder.fileName.setText(mediaBean.getName());
        holder.duration.setText(utils.stringForTime((int) mediaBean.getDuration()));
        holder.fileSize.setText(Formatter.formatFileSize(context,mediaBean.getSize()));
        holder.iv_icon.setImageResource(R.drawable.volume);
    }

    @Override
    public int getItemCount() {
        return datas == null? 0:datas.size();
    }

    class LocalAudioHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView fileName;
        TextView duration;
        TextView fileSize;

        public LocalAudioHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            fileName = (TextView) itemView.findViewById(R.id.fileName);
            duration = (TextView) itemView.findViewById(R.id.duration);
            fileSize = (TextView) itemView.findViewById(R.id.fileSize);
        }
    }
}

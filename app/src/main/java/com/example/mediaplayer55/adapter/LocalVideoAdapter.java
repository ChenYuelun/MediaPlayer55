package com.example.mediaplayer55.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mediaplayer55.R;
import com.example.mediaplayer55.domian.LocalMediaBean;
import com.example.mediaplayer55.utils.Utils;

import java.util.ArrayList;

/**
 * Created by chenyuelun on 2017/5/31.
 */

public class LocalVideoAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<LocalMediaBean> dats;
    private Utils utils;

    public LocalVideoAdapter(Context context, ArrayList<LocalMediaBean> datas) {
        this.context =context;
        this.dats = datas;
        utils = new Utils();
    }

    @Override
    public int getCount() {
        return dats == null? 0:dats.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = View.inflate(context, R.layout.local_meida_item,null);
            viewHolder = new ViewHolder();
            viewHolder.fileName = (TextView) convertView.findViewById(R.id.fileName);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.duration);
            viewHolder.fileSize = (TextView) convertView.findViewById(R.id.fileSize);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LocalMediaBean mediaBean = dats.get(position);
        viewHolder.fileName.setText(mediaBean.getName());
        viewHolder.duration.setText(utils.stringForTime((int) mediaBean.getDuration()));
        viewHolder.fileSize.setText(Formatter.formatFileSize(context,mediaBean.getSize()));
        return convertView;
    }
    static class ViewHolder{
        TextView fileName;
        TextView duration;
        TextView fileSize;
    }
}

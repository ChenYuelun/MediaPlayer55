package com.example.mediaplayer55.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mediaplayer55.R;

import java.util.ArrayList;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<String> datas;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(context, R.layout.test_item,null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_content.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_content;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}

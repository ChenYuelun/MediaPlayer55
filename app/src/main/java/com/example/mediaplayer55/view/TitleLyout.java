package com.example.mediaplayer55.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaplayer55.R;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class TitleLyout extends LinearLayout implements View.OnClickListener {
    private TextView searchbox;
    private RelativeLayout rl_game;
    private ImageView iv_regord;
    private Context context;

    public TitleLyout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        searchbox = (TextView) getChildAt(1);
        rl_game = (RelativeLayout) getChildAt(2);
        iv_regord = (ImageView) getChildAt(3);

        searchbox.setOnClickListener(this);
        rl_game.setOnClickListener(this);
        iv_regord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchbox :
                Toast.makeText(context, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_game :
                Toast.makeText(context, "game", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_regord :
                Toast.makeText(context, "regord", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

package com.example.mediaplayer55.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mediaplayer55.R;
import com.example.mediaplayer55.utils.DensityUtil;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowImageAndGifActivity extends AppCompatActivity {
    private PhotoView activity_show_image_and_gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_and_gif);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        activity_show_image_and_gif = (PhotoView)findViewById(R.id.activity_show_image_and_gif);
        final PhotoViewAttacher attacher = new PhotoViewAttacher(activity_show_image_and_gif);

        ImageOptions imageOptions = new ImageOptions.Builder()
                .setRadius(DensityUtil.dip2px(this, 5))
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.MATRIX)
                .setLoadingDrawableId(R.drawable.video_default)
                .setFailureDrawableId(R.drawable.video_default)
                .build();


        x.image().bind(activity_show_image_and_gif,url, imageOptions, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                attacher.update();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}

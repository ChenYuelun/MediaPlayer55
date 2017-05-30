package com.example.mediaplayer55.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mediaplayer55.R;
import com.example.mediaplayer55.domian.NetAudioBean;
import com.example.mediaplayer55.utils.Utils;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static com.example.mediaplayer55.R.id.tv_content;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.BaseViewHolder> {
    //视频
    private static final int TYPE_VIDEO = 0;
    //图片
    private static final int TYPE_IMAGE = 1;
    //文字
    private static final int TYPE_TEXT = 2;
    //Gif
    private static final int TYPE_GIF = 3;
    //广告
    private static final int TYPE_AD = 4;
    private final Context context;
    private final List<NetAudioBean.ListBean> datas;

    public MyRecyclerViewAdapter(Context context, List<NetAudioBean.ListBean> list) {
        this.context = context;
        this.datas = list;
    }

    @Override
    public int getItemViewType(int position) {
        int ivt = -1;
        switch (datas.get(position).getType()) {
            case "video":
                ivt = TYPE_VIDEO;
                break;
            case "image":
                ivt = TYPE_IMAGE;
                break;
            case "text":
                ivt = TYPE_TEXT;
                break;
            case "gif":
                ivt = TYPE_GIF;
                break;
            default:
                ivt = TYPE_AD;
                break;
        }
        return ivt;
    }

    @Override
    public MyRecyclerViewAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = initViewHolder(viewType);
        return holder;
    }

    private BaseViewHolder initViewHolder(int viewType) {
        BaseViewHolder viewHolder = null;
        View itemView = null;
        switch (viewType) {
            case TYPE_VIDEO:
                itemView = View.inflate(context, R.layout.all_video_item, null);
                viewHolder = new VideoHolder(itemView);
                break;
            case TYPE_IMAGE:
                itemView = View.inflate(context, R.layout.all_image_item, null);
                viewHolder = new ImageHolder(itemView);
                break;
            case TYPE_TEXT:
                itemView = View.inflate(context, R.layout.all_text_item, null);
                viewHolder = new TextHolder(itemView);
                break;
            case TYPE_GIF:
                itemView = View.inflate(context, R.layout.all_gif_item, null);
                viewHolder = new GifHolder(itemView);
                break;
            case TYPE_AD:
                itemView = View.inflate(context, R.layout.test_item, null);
                viewHolder = new AdHolder(itemView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.BaseViewHolder holder, int position) {
        holder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHeadpic;
        TextView tvName;
        TextView tvTimeRefresh;
        ImageView ivRightMore;
        ImageView ivVideoKind;
        TextView tvVideoKindText;
        TextView tvShenheDingNumber;
        TextView tvShenheCaiNumber;
        TextView tvPostsNumber;
        LinearLayout llDownload;

        public BaseViewHolder(View itemView) {
            super(itemView);
            //公共的-头部
            ivHeadpic = (ImageView) itemView.findViewById(R.id.iv_headpic);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvTimeRefresh = (TextView) itemView.findViewById(R.id.tv_time_refresh);
            ivRightMore = (ImageView) itemView.findViewById(R.id.iv_right_more);
            //公共部分-bottom
            ivVideoKind = (ImageView) itemView.findViewById(R.id.iv_video_kind);
            tvVideoKindText = (TextView) itemView.findViewById(R.id.tv_video_kind_text);
            tvShenheDingNumber = (TextView) itemView.findViewById(R.id.tv_shenhe_ding_number);
            tvShenheCaiNumber = (TextView) itemView.findViewById(R.id.tv_shenhe_cai_number);
            tvPostsNumber = (TextView) itemView.findViewById(R.id.tv_posts_number);
            llDownload = (LinearLayout) itemView.findViewById(R.id.ll_download);

        }

        public void setData(NetAudioBean.ListBean listBean) {
            if (listBean.getU() != null && listBean.getU().getHeader() != null && listBean.getU().getHeader().get(0) != null) {
                x.image().bind(ivHeadpic, listBean.getU().getHeader().get(0));
            }
            if (listBean.getU() != null && listBean.getU().getName() != null) {
                tvName.setText(listBean.getU().getName() + "");
            }

            tvTimeRefresh.setText(listBean.getPasstime());

            //设置标签
            List<NetAudioBean.ListBean.TagsEntity> tagsEntities = listBean.getTags();
            if (tagsEntities != null && tagsEntities.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < tagsEntities.size(); i++) {
                    buffer.append(tagsEntities.get(i).getName() + " ");
                }
                tvVideoKindText.setText(buffer.toString());
            }

            //设置点赞，踩,转发

            tvShenheDingNumber.setText(listBean.getUp());
            tvShenheCaiNumber.setText(listBean.getDown() + "");
            tvPostsNumber.setText(listBean.getForward() + "");

        }
    }


    class VideoHolder extends BaseViewHolder {
        Utils utils;
        TextView tvContext;
        JCVideoPlayerStandard jcvVideoplayer;
        TextView tvPlayNums;
        TextView tvVideoDuration;
        ImageView ivCommant;
        TextView tvCommantContext;

        public VideoHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
            utils = new Utils();
            tvPlayNums = (TextView) itemView.findViewById(R.id.tv_play_nums);
            tvVideoDuration = (TextView) itemView.findViewById(R.id.tv_video_duration);
            ivCommant = (ImageView) itemView.findViewById(R.id.iv_commant);
            tvCommantContext = (TextView) itemView.findViewById(R.id.tv_commant_context);
            jcvVideoplayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.jcv_videoplayer);

        }

        public void setData(NetAudioBean.ListBean listBean) {
            super.setData(listBean);
            //设置文本
            tvContext.setText(listBean.getText());
            //视频特有的------------------------
            //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
            boolean setUp = jcvVideoplayer.setUp(
                    listBean.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            //加载图片
            if (setUp) {
//                ImageLoader.getInstance().displayImage(mediaItem.getVideo().getThumbnail().get(0),
//                        jcvVideoplayer.thumbImageView);
                Glide.with(context).load(listBean.getVideo().getThumbnail().get(0)).into(jcvVideoplayer.thumbImageView);
            }
            tvPlayNums.setText(listBean.getVideo().getPlaycount() + "次播放");
            tvVideoDuration.setText(utils.stringForTime(listBean.getVideo().getDuration() * 1000) + "");
        }
    }

    class ImageHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageIcon;

        public ImageHolder(View itemView) {
            super(itemView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
            ivImageIcon = (ImageView) itemView.findViewById(R.id.iv_image_icon);
        }

        public void setData(NetAudioBean.ListBean listBean) {
            super.setData(listBean);
            //设置文本-所有的都有
            tvContext.setText(listBean.getText());
            //图片特有的
            ivImageIcon.setImageResource(R.drawable.video_default);
            if (listBean.getImage() != null && listBean.getImage() != null && listBean.getImage().getThumbnail_small() != null) {
                Glide.with(context)
                        .load(listBean.getImage().getThumbnail_small().get(0))
                        .into(ivImageIcon);
            }
        }
    }

    class TextHolder extends BaseViewHolder {
        TextView tvContext;

        public TextHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
        }

        public void setData(NetAudioBean.ListBean listBean) {
            super.setData(listBean);
            tvContext.setText(listBean.getText());
        }
    }

    class GifHolder extends BaseViewHolder {

        TextView tvContext;
        ImageView ivImageGif;
        private ImageOptions imageOptions;

        public GifHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
            ivImageGif = (ImageView) itemView.findViewById(R.id.iv_image_gif);

            imageOptions = new ImageOptions.Builder()
                    //包裹类型
                    .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, -2)
                    //设置圆角
                    .setRadius(DensityUtil.dip2px(5))
                    .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setLoadingDrawableId(R.drawable.video_default)
                    .setFailureDrawableId(R.drawable.video_default)
                    .build();
        }

        public void setData(NetAudioBean.ListBean listBean) {
            super.setData(listBean);
            tvContext.setText(listBean.getText());

            //下面是gif
            if (listBean.getGif() != null && listBean.getGif() != null && listBean.getGif().getImages() != null) {
                Glide.with(context).load(listBean.getGif().getImages().get(0)).into(ivImageGif);
               // x.image().bind(ivImageGif, listBean.getGif().getImages().get(0), imageOptions);
            }
        }
    }

    class AdHolder extends BaseViewHolder {
        TextView tvContext;
        ImageView ivImageIcon;
        Button btnInstall;

        public AdHolder(View itemView) {
            super(itemView);
            tvContext = (TextView) itemView.findViewById(R.id.tv_context);
            btnInstall = (Button) itemView.findViewById(R.id.btn_install);
            ivImageIcon = (ImageView) itemView.findViewById(R.id.iv_image_icon);
        }

        public void setData(NetAudioBean.ListBean listBean) {
            //super.setData(listBean);这里不能调，会异常
        }
    }
}

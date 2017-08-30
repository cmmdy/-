package com.drop.ttb.mvp.ui.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.DynamicBean;
import com.drop.ttb.mvp.ui.adapter.AdapterComment;
import com.drop.ttb.mvp.ui.adapter.AdapterLike;
import com.drop.ttb.mvp.ui.adapter.AdapterTipsImage;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Drop on 2017/7/28.
 */

public class HolderDynamic extends BaseHolder<DynamicBean> {

    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.title)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.imagelistview)
    RecyclerView imagelistview;
    @BindView(R.id.like)
    ImageView like;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.commentlist)
    RecyclerView commentlist;

    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;

    private DefaultAdapter adapterImages;
    private DefaultAdapter adapterLike;
    private DefaultAdapter adapterComment;

    private List<Integer> images = new ArrayList<>();
    private List<String> likes = new ArrayList<>();
    private List<String> comments = new ArrayList<>();

    private Boolean b;

    public HolderDynamic(View itemView, boolean b) {
        super(itemView);
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
        this.b = b;
    }

    @Override
    public void setData(DynamicBean data, int position) {
        if(!b){
            like.setVisibility(View.INVISIBLE);
            count.setVisibility(View.INVISIBLE);
        }else {
            like.setVisibility(View.VISIBLE);
            count.setVisibility(View.VISIBLE);
        }
        if (adapterImages == null) {
            adapterImages = new AdapterTipsImage(images);
        }
        if (adapterLike == null) {
            adapterLike = new AdapterLike(likes);
        }
        if (adapterComment == null) {
            adapterComment = new AdapterComment(comments);
            UiUtils.configRecycleView(commentlist, new LinearLayoutManager(mAppComponent.appManager().getCurrentActivity()));
        }
        circleImageView.setImageResource(R.drawable.left_head);
//        mImageLoader.loadImage(mAppComponent.appManager().getCurrentActivity() == null
//                        ? mAppComponent.application() : mAppComponent.appManager().getCurrentActivity(),
//                GlideImageConfig
//                        .builder()
//                        .url(data.getImage())
//                        .imageView(circleImageView)
//                        .build());
        name.setText(data.getName());
        time.setText(data.getTime());
        text.setText(data.getText());

//        images.addAll(data.getImages());
        images.add(R.drawable.pic1);
        images.add(R.drawable.pic2);
        images.add(R.drawable.pic3);
        adapterImages.notifyDataSetChanged();
        imagelistview.setAdapter(adapterImages);

//        likes.addAll(data.getLike());
//        adapterLike.notifyDataSetChanged();
//        lickcommentlist.setAdapter(adapterLike);

        count.setText(data.getCount()+"");

        comments.clear();
        comments.addAll(data.getComment());
        adapterComment.notifyDataSetChanged();
        commentlist.setAdapter(adapterComment);


    }
}

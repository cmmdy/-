package com.drop.ttb.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.drop.ttb.R;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;

/**
 * Created by Drop on 2017/7/28.
 */

public class HolderImage extends BaseHolder<Integer> {

    @BindView(R.id.image)
    ImageView image;

    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;

    public HolderImage(View itemView) {
        super(itemView);
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(Integer data, int position) {
//        mImageLoader.loadImage(mAppComponent.appManager().getCurrentActivity() == null
//                        ? mAppComponent.application() : mAppComponent.appManager().getCurrentActivity(),
//                GlideImageConfig
//                        .builder()
//                        .url(data)
//                        .imageView(image)
//                        .build());
        Glide.with(mAppComponent.appManager().getCurrentActivity() == null
                        ? mAppComponent.application() : mAppComponent.appManager().getCurrentActivity())
                .load(data)
                .into(image);
    }
}

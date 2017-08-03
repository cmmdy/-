package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.holder.HolderImage;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

/**
 * Created by Drop on 2017/7/28.
 */

public class AdapterTipsImage extends DefaultAdapter<Integer>{
    public AdapterTipsImage(List<Integer> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Integer> getHolder(View v, int viewType) {
        return new HolderImage(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.imageitem;
    }
}

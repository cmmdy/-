package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.holder.HolderComment;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

/**
 * Created by Drop on 2017/7/28.
 */

public class AdapterComment extends DefaultAdapter<String> {
    public AdapterComment(List<String> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<String> getHolder(View v, int viewType) {
        return new HolderComment(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.commentitem;
    }
}

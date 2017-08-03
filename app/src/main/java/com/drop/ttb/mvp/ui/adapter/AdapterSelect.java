package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.algebra.sdk.entity.Channel;
import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.SelectBean;
import com.drop.ttb.mvp.ui.holder.HolderSelect;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

/**
 * Created by Drop on 2017/7/31.
 */

public class AdapterSelect extends DefaultAdapter<SelectBean> {
    public AdapterSelect(List<SelectBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<SelectBean> getHolder(View v, int viewType) {
        return new HolderSelect(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.selectitem;
    }
}

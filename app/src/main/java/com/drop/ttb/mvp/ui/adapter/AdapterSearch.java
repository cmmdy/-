package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.algebra.sdk.entity.Channel;
import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.holder.HolderMessage;
import com.drop.ttb.mvp.ui.holder.HolderSearch;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

/**
 * Created by Drop on 2017/7/17.
 */

public class AdapterSearch extends DefaultAdapter<Channel> {
    private int week;
    private HolderSearch holderSearch;
    private boolean noSet;

    public AdapterSearch(List<Channel> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Channel> getHolder(View v, int viewType) {
        return holderSearch = new HolderSearch(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycler_list_two;
    }

    public HolderSearch getHolderTwo() {
        return holderSearch;
    }
}

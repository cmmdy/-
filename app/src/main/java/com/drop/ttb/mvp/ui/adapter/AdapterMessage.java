package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.algebra.sdk.entity.Channel;
import com.drop.ttb.R;
import com.drop.ttb.mvp.ui.holder.HolderMessage;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;


/**
 * Created by Drop on 2017/5/15.
 */

public class AdapterMessage extends DefaultAdapter<Channel> {

    private int week;
    private HolderMessage holderMessage;
    private boolean noSet;

    public AdapterMessage(List<Channel> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<Channel> getHolder(View v, int viewType) {
        return holderMessage = new HolderMessage(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycler_list_two;
    }

    public HolderMessage getHolderTwo() {
        return holderMessage;
    }

}

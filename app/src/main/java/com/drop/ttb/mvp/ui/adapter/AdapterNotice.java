package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.algebra.sdk.entity.Channel;
import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.ui.holder.HolderImage;
import com.drop.ttb.mvp.ui.holder.HolderNotice;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

/**
 * Created by Drop on 2017/7/26.
 */

public class AdapterNotice extends DefaultAdapter<GetAllNoticeBean.ResultDataBean> {

    public AdapterNotice(List<GetAllNoticeBean.ResultDataBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<GetAllNoticeBean.ResultDataBean> getHolder(View v, int viewType) {
        return new HolderNotice(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.notice_recyclerview;
    }
}

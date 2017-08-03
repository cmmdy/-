package com.drop.ttb.mvp.ui.adapter;

import android.view.View;

import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.DynamicBean;
import com.drop.ttb.mvp.ui.holder.HolderDynamic;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import static com.amap.api.mapcore.util.ak.b;
import static com.amap.api.mapcore.util.cz.B;

/**
 * Created by Drop on 2017/7/28.
 */

public class AdapterDynamic extends DefaultAdapter<DynamicBean> {

    private Boolean b;

    public AdapterDynamic(List<DynamicBean> infos, boolean b) {
        super(infos);
        this.b = b;
    }

    @Override
    public BaseHolder<DynamicBean> getHolder(View v, int viewType) {
        return new HolderDynamic(v, b);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.tipsitem;
    }
}

package com.drop.ttb.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.SelectBean;
import com.jess.arms.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by Drop on 2017/7/31.
 */

public class HolderSelect extends BaseHolder<SelectBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;

    public HolderSelect(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(SelectBean data, int position) {
        name.setText(data.getId()+"");

    }
}

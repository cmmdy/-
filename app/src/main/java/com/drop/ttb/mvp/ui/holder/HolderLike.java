package com.drop.ttb.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.drop.ttb.R;
import com.jess.arms.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by Drop on 2017/7/28.
 */

public class HolderLike extends BaseHolder<String> {

    @BindView(R.id.name)
    TextView name;

    public HolderLike(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(String data, int position) {
        name.setText(data);
    }
}

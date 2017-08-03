package com.drop.ttb.mvp.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.GetMyMessageBean;
import com.jess.arms.base.BaseHolder;

import butterknife.BindView;

/**
 * Created by Drop on 2017/7/31.
 */

public class HolderMyMessage extends BaseHolder<GetMyMessageBean.ResultDataBean> {

    @BindView(R.id.owner)
    TextView owner;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.notice)
    TextView notice;
    @BindView(R.id.title)
    TextView title;

    public HolderMyMessage(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(GetMyMessageBean.ResultDataBean data, int position) {
        title.setText(data.getCreatid() + "");
        time.setText(data.getCreatdate());
        notice.setText(data.getTextdata());
        owner.setText(data.getChannelname());
    }
}

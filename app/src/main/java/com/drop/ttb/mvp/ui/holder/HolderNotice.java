package com.drop.ttb.mvp.ui.holder;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.ui.activity.ChannelNoticeDetailsActivity;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.jess.arms.widget.imageloader.ImageLoader;

import butterknife.BindView;

import static android.R.attr.data;
import static com.amap.api.mapcore.util.cz.i;
import static com.amap.api.mapcore.util.cz.m;

/**
 * Created by Drop on 2017/7/26.
 */

public class HolderNotice extends BaseHolder<GetAllNoticeBean.ResultDataBean> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.owner)
    TextView owner;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.notice)
    TextView notice;
    @BindView(R.id.rr)
    RelativeLayout rlthis;

    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;

    public HolderNotice(View itemView) {
        super(itemView);
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(GetAllNoticeBean.ResultDataBean data, int position) {
        title.setText("新公告");
        owner.setText(data.getCreatid()+"");
        time.setText(data.getCreatdate());
        if(data.getNoticedata().length() > 50){
            notice.setText(data.getNoticedata().substring(0,47) + "...");
        } else {
            notice.setText(data.getNoticedata());
        }
        rlthis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mAppComponent.appManager().getCurrentActivity(), ChannelNoticeDetailsActivity.class);
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("owner", owner.getText().toString());
                intent.putExtra("time", time.getText().toString());
                intent.putExtra("text", data.getNoticedata());
                intent.putExtra("id", data.getId());
                UiUtils.startActivity(intent);
            }
        });

    }
}

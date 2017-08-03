package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerChannelNoticeDetailsComponent;
import com.drop.ttb.di.module.ChannelNoticeDetailsModule;
import com.drop.ttb.mvp.contract.ChannelNoticeDetailsContract;
import com.drop.ttb.mvp.presenter.ChannelNoticeDetailsPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ChannelNoticeDetailsActivity extends BaseActivity<ChannelNoticeDetailsPresenter> implements ChannelNoticeDetailsContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.owner)
    TextView owner;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.notice)
    TextView notice;

    String noticeTitle;
    String noticeTime;
    String noticeOwner;
    String noticeText;
    int id;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerChannelNoticeDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .channelNoticeDetailsModule(new ChannelNoticeDetailsModule(this))
                .build()
                .inject(this);
        noticeTitle = getIntent().getStringExtra("title");
        noticeTime = getIntent().getStringExtra("time");
        noticeOwner = getIntent().getStringExtra("owner");
        noticeText = getIntent().getStringExtra("text");
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_channel_notice_details; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarTitle.setText("公告详情");
        mytoolbarBack.setVisibility(View.VISIBLE);
        if(TalkActivity.getTalkActivity().owner == LoginActivity.getLoginActivity().uid) {
            mytoolbarConfirm.setText("删除");
            mytoolbarConfirm.setVisibility(View.VISIBLE);
        } else {
            mytoolbarConfirm.setVisibility(View.GONE);
        }
        title.setText(noticeTitle);
        time.setText(noticeTime);
        owner.setText(noticeOwner);
        notice.setText(noticeText);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }



    @OnClick({R.id.mytoolbar_back, R.id.mytoolbar_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mytoolbar_back:
                finish();
                break;
            case R.id.mytoolbar_confirm:
                mPresenter.deleteNotice(id);
                break;
        }
    }
}

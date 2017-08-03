package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerChannelNoticeComponent;
import com.drop.ttb.di.module.ChannelNoticeModule;
import com.drop.ttb.mvp.contract.ChannelNoticeContract;
import com.drop.ttb.mvp.presenter.ChannelNoticePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ChannelNoticeActivity extends BaseActivity<ChannelNoticePresenter> implements ChannelNoticeContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.no)
    TextView no;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.mytoolbar_other)
    ImageView mytoolbarOther;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerChannelNoticeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .channelNoticeModule(new ChannelNoticeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_channel_notice; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarBack.setVisibility(View.VISIBLE);
        mytoolbarOther.setImageResource(R.drawable.icon_addtips);
        if (LoginActivity.getLoginActivity().uid == TalkActivity.getTalkActivity().owner) {
            mytoolbarOther.setVisibility(View.VISIBLE);
        }
        mytoolbarTitle.setText("频道通知");
        mPresenter.requestNotice();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestNotice();
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


    @Override
    public void setAdapter(DefaultAdapter mAdapter) {
        recyclerview.setAdapter(mAdapter);
        UiUtils.configRecycleView(recyclerview, new LinearLayoutManager(this));
    }

    @Override
    public void showno(int i) {
        no.setVisibility(i);
    }


    @OnClick({R.id.mytoolbar_back, R.id.mytoolbar_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mytoolbar_back:
                finish();
                break;
            case R.id.mytoolbar_confirm:
                launchActivity(new Intent(ChannelNoticeActivity.this, AddChannelNoticeActivity.class));
                break;
        }
    }

}

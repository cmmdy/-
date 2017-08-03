package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerAddChannelNoticeComponent;
import com.drop.ttb.di.module.AddChannelNoticeModule;
import com.drop.ttb.mvp.contract.AddChannelNoticeContract;
import com.drop.ttb.mvp.presenter.AddChannelNoticePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class AddChannelNoticeActivity extends BaseActivity<AddChannelNoticePresenter> implements AddChannelNoticeContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.text)
    EditText text;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAddChannelNoticeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addChannelNoticeModule(new AddChannelNoticeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_add_channel_notice; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarTitle.setText("编辑群公告");
        mytoolbarConfirm.setText("发布");
        mytoolbarConfirm.setVisibility(View.VISIBLE);
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
                String noticedata = text.getText().toString();
                mPresenter.setNotice(noticedata, TalkActivity.getTalkActivity().cidId, TalkActivity.getTalkActivity().cidType, LoginActivity.getLoginActivity().uid);
                break;
        }
    }
}

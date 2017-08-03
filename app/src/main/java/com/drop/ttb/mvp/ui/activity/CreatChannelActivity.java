package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerCreatChannelComponent;
import com.drop.ttb.di.module.CreatChannelModule;
import com.drop.ttb.mvp.contract.CreatChannelContract;
import com.drop.ttb.mvp.presenter.CreatChannelPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class CreatChannelActivity extends BaseActivity<CreatChannelPresenter> implements CreatChannelContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.sex_tv)
    EditText sexTv;
    @BindView(R.id.sex_pw)
    EditText sexPw;

    public static String channelName;
    String channelPassword;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCreatChannelComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .creatChannelModule(new CreatChannelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_creat_channel; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarTitle.setText("创建频道");
        mytoolbarConfirm.setVisibility(View.VISIBLE);
        mytoolbarBack.setVisibility(View.VISIBLE);
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



    @OnClick({R.id.mytoolbar_confirm, R.id.mytoolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mytoolbar_confirm:
                channelName = sexTv.getText().toString();
                channelPassword = sexPw.getText().toString();
                LoginActivity.getLoginActivity().channelApi.createPublicChannel(LoginActivity.getLoginActivity().uid,
                        channelName, channelPassword);
                finish();
                break;
            case R.id.mytoolbar_back:
                finish();
                break;
        }
    }
}

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
import com.drop.ttb.di.component.DaggerTipsComponent;
import com.drop.ttb.di.module.TipsModule;
import com.drop.ttb.mvp.contract.TipsContract;
import com.drop.ttb.mvp.presenter.TipsPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class DynamicActivity extends BaseActivity<TipsPresenter> implements TipsContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_other)
    ImageView mytoolbarOther;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    boolean b;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTipsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .tipsModule(new TipsModule(this))
                .build()
                .inject(this);
        int id = getIntent().getIntExtra("id", 0);
        b = getIntent().getBooleanExtra("Channel", false);
        if(b){
            mPresenter.getChannelDynamic();
            mPresenter.getComment();
        } else {
            if(id != 0){
                mPresenter.getUserDynamic(id);
            } else {
                mPresenter.getUserDynamic(LoginActivity.getLoginActivity().uid);
            }
            mPresenter.getComment();
        }

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_tips; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if(b) {
            mytoolbarOther.setVisibility(View.VISIBLE);
            mytoolbarOther.setImageResource(R.drawable.icon_addtips);
            mytoolbarTitle.setText("成员动态");
        } else {
            mytoolbarTitle.setText("我的动态");
        }
        mytoolbarBack.setVisibility(View.VISIBLE);

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getChannelDynamic();
        mPresenter.getComment();
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



    @OnClick({R.id.mytoolbar_back, R.id.mytoolbar_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mytoolbar_back:
                finish();
                break;
            case R.id.mytoolbar_other:
                launchActivity(new Intent(DynamicActivity.this, AddNewDynamicActivity.class));
                break;
        }
    }


    @Override
    public void setDynamicAdapter(DefaultAdapter defaultAdapter) {
        recyclerview.setAdapter(defaultAdapter);
        UiUtils.configRecycleView(recyclerview, new LinearLayoutManager(this));
    }

    @Override
    public boolean getB() {
        return b;
    }
}

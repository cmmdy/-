package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerAddNewDynamicComponent;
import com.drop.ttb.di.module.AddNewDynamicModule;
import com.drop.ttb.mvp.contract.AddNewDynamicContract;
import com.drop.ttb.mvp.presenter.AddNewDynamicPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class AddNewDynamicActivity extends BaseActivity<AddNewDynamicPresenter> implements AddNewDynamicContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.tips)
    EditText tips;
    @BindView(R.id.imagerecyclerview)
    RecyclerView imagerecyclerview;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAddNewDynamicComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addNewDynamicModule(new AddNewDynamicModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_add_new_dynamic; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarBack.setVisibility(View.VISIBLE);
        mytoolbarTitle.setText("发布动态");
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
                mPresenter.addNewDynamic(tips.getText().toString(), "0");
                break;
        }
    }
}

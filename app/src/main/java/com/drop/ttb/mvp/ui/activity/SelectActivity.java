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
import com.drop.ttb.di.component.DaggerSelectComponent;
import com.drop.ttb.di.module.SelectModule;
import com.drop.ttb.mvp.contract.SelectContract;
import com.drop.ttb.mvp.model.entity.SelectBean;
import com.drop.ttb.mvp.presenter.AddNewMessagePresenter;
import com.drop.ttb.mvp.presenter.SelectPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SelectActivity extends BaseActivity<SelectPresenter> implements SelectContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    ArrayList<SelectBean> list = new ArrayList<>();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSelectComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .selectModule(new SelectModule(this))
                .build()
                .inject(this);
        list.addAll(getIntent().getParcelableArrayListExtra("list"));

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_select; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarBack.setVisibility(View.VISIBLE);
        mytoolbarTitle.setText("选择成员");
        mytoolbarConfirm.setVisibility(View.VISIBLE);

        mPresenter.setData();
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
                Intent intent = new Intent(SelectActivity.this, AddNewMessageActivity.class);
                intent.putParcelableArrayListExtra("id", mPresenter.onclick());
                launchActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void setAdapter(DefaultAdapter defaultAdapter) {
        recyclerview.setAdapter(defaultAdapter);
        UiUtils.configRecycleView(recyclerview, new LinearLayoutManager(this));
    }

    @Override
    public ArrayList<SelectBean> getArrayList() {
        return list;
    }
}

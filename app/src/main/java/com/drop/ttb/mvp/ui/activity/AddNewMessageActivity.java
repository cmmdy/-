package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerAddNewMessageComponent;
import com.drop.ttb.di.module.AddNewMessageModule;
import com.drop.ttb.mvp.contract.AddNewMessageContract;
import com.drop.ttb.mvp.model.entity.SelectBean;
import com.drop.ttb.mvp.presenter.AddNewMessagePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class AddNewMessageActivity extends BaseActivity<AddNewMessagePresenter> implements AddNewMessageContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.tips)
    EditText tips;

    ArrayList<SelectBean> arrayList = new ArrayList<>();

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAddNewMessageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .addNewMessageModule(new AddNewMessageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_add_new_message; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarBack.setVisibility(View.VISIBLE);
        mytoolbarTitle.setText("发布消息");
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


    @Override
    public String getText() {
        return tips.getText().toString();
    }


    @OnClick(R.id.mytoolbar_confirm)
    public void onViewClicked() {
        arrayList.addAll(getIntent().getParcelableArrayListExtra("id"));
        boolean b = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (i + 1 == arrayList.size()) {
                b = true;
            }
            mPresenter.addNewMessage(arrayList.get(i).getId(), b);
        }
    }
}

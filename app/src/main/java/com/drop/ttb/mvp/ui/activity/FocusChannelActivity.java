package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.algebra.sdk.entity.Channel;
import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerFocusChannelComponent;
import com.drop.ttb.di.module.FocusChannelModule;
import com.drop.ttb.mvp.contract.FocusChannelContract;
import com.drop.ttb.mvp.presenter.FocusChannelPresenter;
import com.drop.ttb.mvp.ui.adapter.AdapterMessage;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class FocusChannelActivity extends BaseActivity<FocusChannelPresenter> implements FocusChannelContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    List<Channel> list = new ArrayList<>();
    AdapterMessage adapterMessage = new AdapterMessage(list);


    static FocusChannelActivity focusChannelActivity = null;


    public static boolean focus = false;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerFocusChannelComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .focusChannelModule(new FocusChannelModule(this))
                .build()
                .inject(this);

        focus = true;
        focusChannelActivity = this;
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_focus_channel; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarTitle.setText("添加频道");
        mytoolbarBack.setVisibility(View.VISIBLE);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LoginActivity.getLoginActivity().channelApi.searchPublicChannel(
                        LoginActivity.getLoginActivity().uid, s.toString()
                );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        UiUtils.configRecycleView(recyclerview, new LinearLayoutManager(this));
        recyclerview.setAdapter(adapterMessage);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public static FocusChannelActivity getFocusChannelActivity() {
        return focusChannelActivity;
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
    protected void onDestroy() {
        focus = false;
        super.onDestroy();
    }

    @OnClick(R.id.mytoolbar_back)
    public void onViewClicked() {
        finish();
    }

}

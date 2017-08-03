package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerTipsDetailsComponent;
import com.drop.ttb.di.module.TipsDetailsModule;
import com.drop.ttb.mvp.contract.TipsDetailsContract;
import com.drop.ttb.mvp.presenter.TipsDetailsPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class TipsDetailsActivity extends BaseActivity<TipsDetailsPresenter> implements TipsDetailsContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.circleImageView)
    CircleImageView circleImageView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.imagelistview)
    RecyclerView imagelistview;
    @BindView(R.id.commentlist)
    RecyclerView commentlist;
    @BindView(R.id.commenttext)
    EditText commenttext;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.like)
    ImageView like;
    @BindView(R.id.count)
    TextView count;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTipsDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .tipsDetailsModule(new TipsDetailsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_tips_details; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

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



    @OnClick({R.id.mytoolbar_back, R.id.comment, R.id.like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mytoolbar_back:
                finish();
                break;
            case R.id.comment:
                break;
            case R.id.like:
                break;
        }
    }

    @Override
    public void setImageAdapter(DefaultAdapter defaultAdapter) {
        imagelistview.setAdapter(defaultAdapter);
        UiUtils.configRecycleView(imagelistview, new GridLayoutManager(TipsDetailsActivity.this, 4));
    }
}

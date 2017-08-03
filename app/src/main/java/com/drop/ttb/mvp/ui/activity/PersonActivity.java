package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerPersonComponent;
import com.drop.ttb.di.module.PersonModule;
import com.drop.ttb.mvp.contract.PersonContract;
import com.drop.ttb.mvp.presenter.PersonPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class PersonActivity extends BaseActivity<PersonPresenter> implements PersonContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.name)
    TextView nametv;
    @BindView(R.id.location)
    TextView locationtv;
    @BindView(R.id.birthday)
    TextView birthdaytv;
    @BindView(R.id.phone)
    TextView phonetv;

    int id;
    String name;
    String phone;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerPersonComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .personModule(new PersonModule(this))
                .build()
                .inject(this);

        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_person; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarBack.setVisibility(View.VISIBLE);
        mytoolbarTitle.setText("成员详情");

        nametv.setText(name);
        phonetv.setText(phone);
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



    @OnClick({R.id.dynamic, R.id.chat, R.id.up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dynamic:
                Intent intent = new Intent(PersonActivity.this, DynamicActivity.class);
                intent.putExtra("id", id);
                launchActivity(intent);
                break;
            case R.id.chat:
                break;
            case R.id.up:
                break;
        }
    }
}

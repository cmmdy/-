package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerSetNameComponent;
import com.drop.ttb.di.module.SetNameModule;
import com.drop.ttb.mvp.contract.SetNameContract;
import com.drop.ttb.mvp.presenter.SetNamePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SetNameActivity extends BaseActivity<SetNamePresenter> implements SetNameContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.setname)
    EditText setname;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;

    int cidType;
    int cidId;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSetNameComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .setNameModule(new SetNameModule(this))
                .build()
                .inject(this);
        cidType = getIntent().getIntExtra("ctype", 0);
        cidId = getIntent().getIntExtra("cid", 0);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_set_name; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarTitle.setText("频道名称");
        mytoolbarBack.setVisibility(View.VISIBLE);
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
                if(!setname.getText().toString().equals("")){
                    LoginActivity.getLoginActivity().channelApi.changePublicChannelName(
                            LoginActivity.getLoginActivity().uid, cidType, cidId, setname.getText().toString()
                    );
                    Intent intent = new Intent(setname.getText().toString());
                    intent.putExtra("name", intent);
                    finish();
                } else {
                    UiUtils.snackbarText("频道名不能为空");
                }
                break;
        }
    }
}

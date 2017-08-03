package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerRegisterComponent;
import com.drop.ttb.di.module.RegisterModule;
import com.drop.ttb.mvp.contract.RegisterContract;
import com.drop.ttb.mvp.presenter.RegisterPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {


    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.getverification)
    ImageView getverification;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm)
    EditText confirm;
    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisterComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_register; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarBack.setVisibility(View.VISIBLE);
        mytoolbarTitle.setText("注册账号");
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

    //创建弹出对话框
    private void creatAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
        alertDialog.show();
        WindowManager.LayoutParams p = alertDialog.getWindow().getAttributes();
        p.height = (int) (UiUtils.getScreenHeidth(getApplicationContext()) * 0.2);
        p.width = (int) (UiUtils.getScreenWidth(getApplicationContext()) * 0.65);
        Window window = alertDialog.getWindow();
        window.setAttributes(p);
        window.setContentView(R.layout.my_alertdialog);
        window.findViewById(R.id.confirm).setOnClickListener((v) -> alertDialog.dismiss());
    }


    @OnClick({R.id.getverification, R.id.next,R.id.mytoolbar_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.getverification:
//                String phone = account.getText().toString();
//                if (phone.length() == 11) {
//                    LoginActivity.getLoginActivity().accountApi.requestBindingPhone(selfId, userAccount,
//                            phone);
//                } else {
//                    Toast.makeText(uiContext, getResources().getString(R.string.input_format_error),
//                            Toast.LENGTH_SHORT).show();
//                    showBindingPhone();
//                }
//                break;
            case R.id.next:
                creatAlertDialog();
                break;
            case R.id.mytoolbar_back:
                finish();
                break;
        }
    }


}

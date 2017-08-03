package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.algebra.sdk.API;
import com.algebra.sdk.AccountApi;
import com.algebra.sdk.ChannelApi;
import com.algebra.sdk.DeviceApi;
import com.algebra.sdk.OnAccountListener;
import com.algebra.sdk.SessionApi;
import com.algebra.sdk.entity.Constant;
import com.algebra.sdk.entity.Contact;
import com.algebra.sdk.entity.UserProfile;
import com.bumptech.glide.Glide;
import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerLoginComponent;
import com.drop.ttb.di.module.LoginModule;
import com.drop.ttb.mvp.contract.LoginContract;
import com.drop.ttb.mvp.presenter.LoginPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, OnAccountListener {


    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;

    private String userAccount = null;
    private String userPass = null;
    private String userNick = "???";
    private String userPhone = null;
    private boolean userBoundPhone = false;
    public AccountApi accountApi = null;
    public DeviceApi deviceApi = null;
    private static Handler uiHandler = null;
    private AppComponent appComponent;
    public int uid;
    public ChannelApi channelApi = null;
    public SessionApi sessionApi = null;
    Contact contact;


    private static LoginActivity loginActivity = null;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
        this.appComponent = appComponent;
        API.init(LoginActivity.this);
        uiHandler = new Handler();
        uiHandler.postDelayed(delayInitApi, 300);

        loginActivity = this;

        if (ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(LoginActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                    android.Manifest.permission.READ_PHONE_STATE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1
            );
        }
    }

    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    boolean isRealFinish = false;
    @Override
    public void finish() {
        if (isRealFinish) {
            super.finish();
            this.moveTaskToBack(true);
        } else {
            this.moveTaskToBack(false);
        }
        isRealFinish = false;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        Glide.with(this).load(R.drawable.login_background).into(background);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);


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


    @OnClick({R.id.forgetpassword, R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forgetpassword:
                launchActivity(new Intent(this, PasswordActivity.class));
                break;
            case R.id.register:
                launchActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login:
                userAccount = account.getText().toString();
                userPass = md5(password.getText().toString());
                accountApi.login(userAccount, userPass);
                break;
        }
    }

    private Runnable delayInitApi = new Runnable() {
        @Override
        public void run() {
            accountApi = API.getAccountApi();
            deviceApi = API.getDeviceApi();
            channelApi = API.getChannelApi();
            sessionApi = API.getSessionApi();
            if (accountApi != null && deviceApi != null) {
                accountApi.setOnAccountListener(LoginActivity.this);
                Contact me = accountApi.whoAmI();
                if (me != null) {

                }
            } else {
                Log.e("300",

                        "start SDK and waiting another 300ms.");
                uiHandler.postDelayed(delayInitApi, 300);
            }
        }
    };

    public static LoginActivity getLoginActivity() {
        return loginActivity;
    }

//    public void bindingPhone(){
//        accountApi.requestBindingPhone(selfId, userAccount,
//                phone);
//    }

    @Override
    public void onLogin(int uid, int result, UserProfile uProfile) {
        if (result == Constant.ACCOUNT_RESULT_OK
                || result == Constant.ACCOUNT_RESULT_ALREADY_LOGIN) {
            if (uProfile != null) {
                userBoundPhone = !uProfile.userPhone.equals("none");
                userAccount = new String(uProfile.userName);
                userNick = new String(uProfile.userNick);
            }
            this.uid = uid;
            contact = accountApi.whoAmI();
            if (contact != null) {
                this.uid = contact.id;
            }
            launchActivity(new Intent(this, MainActivity.class));
            return;
        }
    }

    @Override
    public void onCreateUser(int i, int i1, String s) {

    }

    @Override
    public void onLogout() {

    }

    @Override
    public void onSetNickName(int i) {

    }

    @Override
    public void onChangePassWord(int i, boolean b) {

    }

    @Override
    public void onAskUnbind(int i) {

    }

    @Override
    public void onAuthRequestReply(int i, int i1, String s) {

    }

    @Override
    public void onAuthBindingReply(int i, int i1, String s) {

    }

    @Override
    public void onAuthRequestPassReply(int i, int i1, String s) {

    }

    @Override
    public void onAuthResetPassReply(int i, int i1) {

    }

    @Override
    public void onFriendsSectionGet(int i, int i1, int i2, int i3, List<Contact> list) {

    }

    @Override
    public void onFriendStatusUpdate(int i, int i1, int i2) {

    }

    @Override
    public void onShakeScreenAck(int i, int i1, int i2) {

    }

    @Override
    public void onShakeScreenReceived(int i, String s, String s1) {

    }

    @Override
    public void onSetStatusReturn(int i, boolean b) {

    }

    @Override
    public void onHearbeatLost(int i, int i1) {

    }

    @Override
    public void onKickedOut(int i, int i1) {

    }

    @Override
    public void onSelfStateChange(int i, int i1) {

    }

    @Override
    public void onSelfLocationAvailable(int i, Double aDouble, Double aDouble1, Double aDouble2) {

    }

    @Override
    public void onSelfLocationReported(int i) {

    }

    @Override
    public void onUserLocationNotify(int i, String s, Double aDouble, Double aDouble1) {

    }

    @Override
    public void onLogger(int i, String s) {

    }

    @Override
    public void onSmsRequestReply(int i) {

    }

    public static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] strTemp = s.getBytes();
            //使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                //System.out.println((int)b);
                //将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}

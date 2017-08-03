package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algebra.sdk.entity.Contact;
import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerChannelManageComponent;
import com.drop.ttb.di.module.ChannelManageModule;
import com.drop.ttb.mvp.contract.ChannelManageContract;
import com.drop.ttb.mvp.model.entity.SelectBean;
import com.drop.ttb.mvp.presenter.ChannelManagePresenter;
import com.drop.ttb.mvp.ui.adapter.AdapterContact;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ChannelManageActivity extends BaseActivity<ChannelManagePresenter> implements ChannelManageContract.View {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_share)
    ImageView mytoolbarShare;
    @BindView(R.id.mytoolbar_other)
    ImageView mytoolbarOther;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.mytoolbar_message)
    ImageView mytoolbarMessage;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.setname)
    LinearLayout setname;
    @BindView(R.id.setNotice)
    RelativeLayout setNotice;
    @BindView(R.id.spots)
    RelativeLayout spots;
    @BindView(R.id.delete)
    RelativeLayout delete;
    @BindView(R.id.name)
    TextView name1;

    static ChannelManageActivity channelManageActivity;

    List<Contact> list = new ArrayList<>();
    String name = "";
    int ctype;
    int cid;
    @BindView(R.id.imageView2)
    ImageView imageView2;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerChannelManageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .channelManageModule(new ChannelManageModule(this))
                .build()
                .inject(this);
        name = getIntent().getStringExtra("name");
        ctype = getIntent().getIntExtra("ctype", 0);
        cid = getIntent().getIntExtra("cid", 0);
        list.addAll(MainActivity.getMainActivity().list1);
        channelManageActivity = this;
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_channel_manage; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        AdapterContact adapterContact = new AdapterContact(list);
        UiUtils.configRecycleView(recyclerView, new GridLayoutManager(this, 5));
        recyclerView.setAdapter(adapterContact);

        mytoolbarTitle.setText("频道管理  ");
        mytoolbarBack.setVisibility(View.VISIBLE);
        name1.setText(name);

        if(LoginActivity.getLoginActivity().uid != TalkActivity.getTalkActivity().owner){
            imageView2.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.getDynamic();
//        mPresenter.requestNotice();
    }

    public static ChannelManageActivity getChannelManageActivity() {
        return channelManageActivity;
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


    @OnClick({R.id.mytoolbar_back, R.id.setname, R.id.setNotice, R.id.spots, R.id.delete, R.id.notice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mytoolbar_back:
                finish();
                break;
            case R.id.setname:
                if (LoginActivity.getLoginActivity().uid == TalkActivity.getTalkActivity().owner) {
                    Intent intent = new Intent(ChannelManageActivity.this, SetNameActivity.class);
                    intent.putExtra("ctype", ctype);
                    intent.putExtra("cid", cid);
                    launchActivity(intent);
                }
                break;
            case R.id.setNotice:
                Intent intents = new Intent(ChannelManageActivity.this, ChannelNoticeActivity.class);
                launchActivity(intents);
                break;
            case R.id.spots:
                Intent intentp = new Intent(ChannelManageActivity.this, DynamicActivity.class);
                intentp.putExtra("Channel", true);
                launchActivity(intentp);
                break;
            case R.id.delete:
                mPresenter.deleteChannel();
                LoginActivity.getLoginActivity().channelApi.unfocusPublicChannel(
                        LoginActivity.getLoginActivity().uid, ctype, cid
                );
                break;
            case R.id.notice:
                ArrayList<SelectBean> selectBeen = new ArrayList<>();
                SelectBean s;
                for (int i = 0; i < list.size(); i++) {
                    s = new SelectBean(list.get(i).id, list.get(i).name, 0, false);
                    selectBeen.add(s);
                }
                Intent intent1 = new Intent(ChannelManageActivity.this, SelectActivity.class);
                intent1.putParcelableArrayListExtra("list", selectBeen);
                launchActivity(intent1);
        }
    }

    @Override
    public int getId() {
        return cid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

//    @Override
//    public void setDynamic(String i) {
//        if(i.length() > 140){
//            i = i.substring(0,135) + "...";
//        }
//        CDynamic.setText(i);
//    }
//
//    @Override
//    public void setNotice(String i) {
//        if(i.length() > 140){
//            i = i.substring(0,135) + "...";
//        }
//        Cnotice.setText(i);
//    }

}

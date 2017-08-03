package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algebra.sdk.OnChannelListener;
import com.algebra.sdk.OnSessionListener;
import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Constant;
import com.algebra.sdk.entity.Contact;
import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerMainComponent;
import com.drop.ttb.di.module.MainModule;
import com.drop.ttb.mvp.contract.MainContract;
import com.drop.ttb.mvp.presenter.MainPresenter;
import com.drop.ttb.mvp.ui.adapter.AdapterMessage;
import com.drop.ttb.mvp.ui.holder.HolderMessage;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, OnChannelListener, OnSessionListener {

    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_share)
    ImageView mytoolbarShare;
    @BindView(R.id.mytoolbar_other)
    ImageView mytoolbarOther;
    @BindView(R.id.mytoolbar_confirm)
    TextView mytoolbarConfirm;
    @BindView(R.id.recyclerview_week)
    RecyclerView recyclerviewWeek;
    @BindView(R.id.creat)
    RelativeLayout creat;
    @BindView(R.id.add)
    RelativeLayout add;
    @BindView(R.id.all)
    RelativeLayout all;
    AppManager appManager;
    @BindView(R.id.mytoolbar_message)
    ImageView mytoolbarMessage;
    PopupWindow popWnd;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;


    public List<Channel> list = new ArrayList<>();
    public List<Contact> list1 = new ArrayList<>();
    AdapterMessage adapterMessage = new AdapterMessage(list);
    boolean popwindow = false;

    static MainActivity mainActivity;

    private int resultCode;
    private String resultMessage;
    private ResultDataBean resultData;

    @Override
    protected void onDestroy() {
        LoginActivity.getLoginActivity().accountApi.logout(LoginActivity.getLoginActivity().uid);
        super.onDestroy();
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
        this.appManager = appComponent.appManager();
        LoginActivity.getLoginActivity().channelApi.setOnChannelListener(this);
        LoginActivity.getLoginActivity().sessionApi.setOnSessionListener(this);
        LoginActivity.getLoginActivity().channelApi.adverChannelsGet(LoginActivity.getLoginActivity().uid);
        LoginActivity.getLoginActivity().channelApi.channelListGet(LoginActivity.getLoginActivity().uid);
        mainActivity = this;

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        recyclerviewWeek.setAdapter(adapterMessage);
        UiUtils.configRecycleView(recyclerviewWeek, new LinearLayoutManager(this));

        mytoolbarTitle.setText("我的频道           ");
        mytoolbarMessage.setVisibility(View.VISIBLE);
        popWnd = new PopupWindow(MainActivity.this);
        popWnd.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = MainActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1.0f;
                MainActivity.this.getWindow().setAttributes(lp);
                popwindow = false;
            }
        });
        mytoolbarMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!popwindow) {
                    View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popwindow_message, null);
                    RelativeLayout creat = (RelativeLayout) contentView.findViewById(R.id.creat);
                    RelativeLayout add = (RelativeLayout) contentView.findViewById(R.id.add);
                    creat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popWnd.dismiss();
                            launchActivity(new Intent(MainActivity.this, CreatChannelActivity.class));
                        }
                    });
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popWnd.dismiss();
                            launchActivity(new Intent(MainActivity.this, FocusChannelActivity.class));
                        }
                    });
                    popWnd.setContentView(contentView);
                    popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                    popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    popWnd.setBackgroundDrawable(null);
                    WindowManager.LayoutParams lp = MainActivity.this.getWindow()
                            .getAttributes();
                    lp.alpha = 0.5f;
                    MainActivity.this.getWindow().setAttributes(lp);
                    popwindow = true;
                    popWnd.setBackgroundDrawable(new BitmapDrawable());
                    popWnd.setFocusable(true);
                    popWnd.showAsDropDown(v, 10, 20);

                } else {
                    popWnd.dismiss();
                }

            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
        navView.setCheckedItem(R.id.nav_message);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_message:
                        launchActivity(new Intent(MainActivity.this, MyMessageActivity.class));
                        break;
                    case R.id.nav_forget:
                        break;
                    case R.id.nav_spots:
                        launchActivity(new Intent(MainActivity.this, DynamicActivity.class));
                        break;
                }
                return false;
            }
        });

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onPause() {
        super.onPause();
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
    public void onDefaultChannelSet(int i, int i1, int i2) {

    }

    @Override
    public void onAdverChannelsGet(int i, Channel channel, List<Channel> list) {
//        this.list.addAll(list);
//        if (this.list.size() > 0) {
//            all.setVisibility(View.GONE);
//        }
//        adapterMessage.notifyDataSetChanged();
//        recyclerviewWeek.setAdapter(adapterMessage);
    }

    @Override
    public void onChannelListGet(int i, Channel channel, List<Channel> list) {
        this.list.addAll(list);
        mPresenter.setData();
        if (this.list.size() > 0) {
            all.setVisibility(View.GONE);
        }
        adapterMessage.notifyDataSetChanged();
        recyclerviewWeek.setAdapter(adapterMessage);
    }


    @Override
    public void onChannelMemberListGet(int i, int i1, int i2, List<Contact> list) {
        list1.clear();
        list1.addAll(list);
        String name = "";
        for (int j = 0; j < this.list.size(); j++) {
            if (this.list.get(j).cid.getId() == i2) {
                name = this.list.get(j).name;
            }
        }
        Intent intent = new Intent(MainActivity.this, ChannelManageActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("ctype", i1);
        intent.putExtra("cid", i2);
        launchActivity(intent);
    }

    @Override
    public void onChannelNameChanged(int i, int i1, int i2, String s) {
        TalkActivity.getTalkActivity().mytoolbarTitle.setText(s);
        ChannelManageActivity.getChannelManageActivity().name1.setText(s);
    }

    @Override
    protected void onResume() {
        Log.e("resume", "resume");
        adapterMessage.notifyDataSetChanged();
        recyclerviewWeek.setAdapter(adapterMessage);
        super.onResume();
    }

    @Override
    public void onChannelAdded(int i, int i1, int i2, String s) {
        Channel channel = new Channel(i1, i2, s);
        list.add(channel);
        mPresenter.setData();
        if (this.list.size() > 0) {
            all.setVisibility(View.GONE);
        }
        adapterMessage.notifyDataSetChanged();
        recyclerviewWeek.setAdapter(adapterMessage);
    }

    @Override
    public void onChannelRemoved(int i, int i1, int i2) {

    }

    @Override
    public void onChannelMemberAdded(int i, int i1, List<Contact> list) {

    }

    @Override
    public void onChannelMemberRemoved(int i, int i1, List<Integer> list) {

    }

    @Override
    public void onPubChannelCreate(int i, int i1, int i2) {
        Channel channel = new Channel(Constant.SESSION_TYPE_CHANNEL, i2, CreatChannelActivity.channelName);
        list.add(channel);
        if (this.list.size() > 0) {
            all.setVisibility(View.GONE);
        }
        adapterMessage.notifyDataSetChanged();
        recyclerviewWeek.setAdapter(adapterMessage);
    }

    @Override
    public void onPubChannelSearchResult(int i, List<Channel> list) {
        FocusChannelActivity.getFocusChannelActivity().list.clear();
        FocusChannelActivity.getFocusChannelActivity().list.addAll(list);
        FocusChannelActivity.getFocusChannelActivity().adapterMessage.notifyDataSetChanged();
        FocusChannelActivity.getFocusChannelActivity().recyclerview.setAdapter(FocusChannelActivity.getFocusChannelActivity().adapterMessage);
    }

    @Override
    public void onPubChannelFocusResult(int i, int i1) {
        if (i > 0) {
            list.add(HolderMessage.channel);
            if (this.list.size() > 0) {
                all.setVisibility(View.GONE);
            }
            adapterMessage.notifyDataSetChanged();
            recyclerviewWeek.setAdapter(adapterMessage);
        } else {
            Toast.makeText(this, "频道关注失败, " + pubChErrorString(i1),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private String pubChErrorString(int reason) {
        if (reason == Constant.E_PUBCH_OVERLIMIT)
            return "长度超限";
        if (reason == Constant.E_PUBCH_PASSWORD)
            return "密码错误";

        return "未知错误";
    }

    @Override
    public void onPubChannelUnfocusResult(int i, int i1) {
        if (i > 0) {
            for (int j = 0; j < this.list.size(); j++) {
                if (this.list.get(j).cid.getId() == ChannelManageActivity.getChannelManageActivity().cid) {
                    this.list.remove(j);
                }
            }
            adapterMessage.notifyDataSetChanged();
            recyclerviewWeek.setAdapter(adapterMessage);
            ChannelManageActivity.getChannelManageActivity().killMyself();
            TalkActivity.getTalkActivity().killMyself();
            UiUtils.snackbarText("频道移除成功");
        }
    }

    @Override
    public void onPubChannelRenamed(int i, int i1) {

    }

    @Override
    public void onPubChannelDeleted(int i, int i1) {

    }

    @Override
    public void onCallMeetingStarted(int i, int i1, int i2, List<Contact> list) {

    }

    @Override
    public void onCallMeetingStopped(int i, int i1) {

    }

    @Override
    public void onSessionEstablished(int i, int i1, int i2) {
    }

    @Override
    public void onSessionReleased(int i, int i1, int i2) {

    }

    @Override
    public void onSessionGet(int i, int i1, int i2, int i3) {

    }

    @Override
    public void onSessionPresenceAdded(int i, int i1, List<Contact> list) {

    }

    @Override
    public void onSessionPresenceRemoved(int i, int i1, List<Integer> list) {

    }

    @Override
    public void onDialogEstablished(int i, int i1, int i2, List<Integer> list) {

    }

    @Override
    public void onDialogLeaved(int i, int i1) {

    }

    @Override
    public void onDialogPresenceAdded(int i, int i1, List<Integer> list) {

    }

    @Override
    public void onDialogPresenceRemoved(int i, int i1, List<Integer> list) {

    }

    @OnClick({R.id.creat, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.creat:
                launchActivity(new Intent(MainActivity.this, CreatChannelActivity.class));
                break;
            case R.id.add:
                launchActivity(new Intent(MainActivity.this, FocusChannelActivity.class));
                break;
        }
    }




    @Override
    public List<Channel> getChannelList() {
        return this.list;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }


    public static class ResultDataBean {
        /**
         * id : 10
         * noticedata : 1111
         * channelid : 5541
         * creatid : 5541
         * creatdate : 2017-07-26T20:24:24
         * channeltype : 2
         */

        private int id;
        private String noticedata;
        private int channelid;
        private int creatid;
        private String creatdate;
        private int channeltype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNoticedata() {
            return noticedata;
        }

        public void setNoticedata(String noticedata) {
            this.noticedata = noticedata;
        }

        public int getChannelid() {
            return channelid;
        }

        public void setChannelid(int channelid) {
            this.channelid = channelid;
        }

        public int getCreatid() {
            return creatid;
        }

        public void setCreatid(int creatid) {
            this.creatid = creatid;
        }

        public String getCreatdate() {
            return creatdate;
        }

        public void setCreatdate(String creatdate) {
            this.creatdate = creatdate;
        }

        public int getChanneltype() {
            return channeltype;
        }

        public void setChanneltype(int channeltype) {
            this.channeltype = channeltype;
        }
    }
}
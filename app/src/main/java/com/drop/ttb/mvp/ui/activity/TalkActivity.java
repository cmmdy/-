package com.drop.ttb.mvp.ui.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.algebra.sdk.OnMediaListener;
import com.algebra.sdk.entity.CompactID;
import com.algebra.sdk.entity.HistoryRecord;
import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerTalkComponent;
import com.drop.ttb.di.module.TalkModule;
import com.drop.ttb.mvp.contract.TalkContract;
import com.drop.ttb.mvp.model.entity.TalkHistory;
import com.drop.ttb.mvp.presenter.TalkPresenter;
import com.drop.ttb.mvp.ui.adapter.MsgAdapter;
import com.drop.ttb.mvp.ui.holder.HolderMessage;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.drop.ttb.R.layout.dialog;
import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.drop.ttb.mvp.model.entity.TalkHistory.AMR475_PL_SIZE;


public class TalkActivity extends BaseActivity<TalkPresenter> implements TalkContract.View, OnMediaListener {


    @BindView(R.id.mytoolbar_back)
    ImageView mytoolbarBack;
    @BindView(R.id.mytoolbar_title)
    TextView mytoolbarTitle;
    @BindView(R.id.mytoolbar_share)
    ImageView mytoolbarShare;
    @BindView(R.id.mytoolbar_other)
    ImageView mytoolbarOther;
    @BindView(R.id.msg_recycler_view)
    RecyclerView msgRecyclerView;
    @BindView(R.id.imageView9)
    ImageView imageView9;
    @BindView(R.id.switchin)
    Switch switchin;
    @BindView(R.id.switchintv)
    TextView switchintv;
    @BindView(R.id.switchmanage)
    Switch switchmanage;
    @BindView(R.id.switchmanagetv)
    TextView switchmanagetv;

    List<HistoryRecord> historyRecords = new ArrayList<>();
    MsgAdapter msgAdapter = new MsgAdapter(historyRecords);

    static TalkActivity talkActivity;
    String name;
    int cidType;
    int cidId;
    int owner;
    boolean popwindow = false;
    PopupWindow popWnd;
    private TalkHistory talkHistory = null;
    CompactID compactID;
    int spots = 0;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTalkComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .talkModule(new TalkModule(this))
                .build()
                .inject(this);
        name = getIntent().getStringExtra("name");
        cidType = getIntent().getIntExtra("cidType", 0);
        cidId = getIntent().getIntExtra("cidId", 0);
        owner = getIntent().getIntExtra("owner", 0);
        talkActivity = this;
        talkHistory = new TalkHistory(LoginActivity.getLoginActivity().uid);
        compactID = new CompactID(cidType, cidId);
        talkHistory.openFiles4Write(LoginActivity.getLoginActivity().uid, compactID.getCompactId());
        HistoryRecord[] hisRecs = talkHistory.getAllHistoryRecords(compactID.getCompactId());
        for (int i = 0; i < hisRecs.length; i++) {
            hisRecs[i].mediaData = new byte[hisRecs[i].duration / 2 * AMR475_PL_SIZE];
            talkHistory.readSpeechBuffer(compactID.getCompactId(), i + 1, hisRecs[i].mediaData, 0);
            historyRecords.add(hisRecs[hisRecs.length - 1 - i]);
        }
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_talk; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    protected void onDestroy() {
        compactID = null;
        talkHistory.closeFiles();
        super.onDestroy();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mytoolbarBack.setVisibility(View.VISIBLE);
        mytoolbarShare.setVisibility(View.VISIBLE);
        mytoolbarOther.setVisibility(View.VISIBLE);
        mytoolbarTitle.setText(HolderMessage.channel.name);
        LoginActivity.getLoginActivity().sessionApi.setOnMediaListener(this);

        imageView9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    LoginActivity.getLoginActivity().sessionApi.talkRequest(
                            LoginActivity.getLoginActivity().uid, HolderMessage.channel.cid.getType(), HolderMessage.channel.cid.getId()
                    );
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    LoginActivity.getLoginActivity().sessionApi.talkRelease(
                            LoginActivity.getLoginActivity().uid, HolderMessage.channel.cid.getType(), HolderMessage.channel.cid.getId()
                    );
                }
                return false;
            }
        });

        msgRecyclerView.setAdapter(msgAdapter);
        UiUtils.configRecycleView(msgRecyclerView, new LinearLayoutManager(this));

        switchin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LoginActivity.getLoginActivity().sessionApi.sessionCall(
                            LoginActivity.getLoginActivity().uid, cidType, cidId
                    );
                    switchintv.setText("上线");
                } else {
                    LoginActivity.getLoginActivity().sessionApi.sessionBye(
                            LoginActivity.getLoginActivity().uid, cidType, cidId
                    );
                    switchintv.setText("下线");
                }
            }
        });
        if (owner != LoginActivity.getLoginActivity().uid) {
            switchmanage.setVisibility(View.GONE);
            switchmanagetv.setVisibility(View.GONE);
        }
        switchmanage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spots = 1;
                    mPresenter.setData();
                    LoginActivity.getLoginActivity().sessionApi.talkRequest(
                            LoginActivity.getLoginActivity().uid, HolderMessage.channel.cid.getType(), HolderMessage.channel.cid.getId()
                    );
                    switchmanagetv.setText("正在插播");
                } else {
                    spots = 0;
                    mPresenter.setData();
                    LoginActivity.getLoginActivity().sessionApi.talkRelease(
                            LoginActivity.getLoginActivity().uid, HolderMessage.channel.cid.getType(), HolderMessage.channel.cid.getId()
                    );
                    switchmanagetv.setText("插播");
                }
            }
        });

        if (LoginActivity.getLoginActivity().uid != owner) {
            switchmanage.setVisibility(View.GONE);
            switchmanagetv.setVisibility(View.GONE);
        }

        popWnd = new PopupWindow(TalkActivity.this);
        popWnd.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = TalkActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1.0f;
                TalkActivity.this.getWindow().setAttributes(lp);
                popwindow = false;
            }
        });

        mytoolbarShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!popwindow) {
                    View contentView = LayoutInflater.from(TalkActivity.this).inflate(R.layout.popwindow_talk, null);
                    RelativeLayout shareLocation = (RelativeLayout) contentView.findViewById(R.id.share_location);
                    RelativeLayout shareSituation = (RelativeLayout) contentView.findViewById(R.id.share_situation);
                    RelativeLayout shareImage = (RelativeLayout) contentView.findViewById(R.id.share_image);
                    shareLocation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popWnd.dismiss();
                            launchActivity(new Intent(TalkActivity.this, MapActivity.class));
                        }
                    });
                    shareSituation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popWnd.dismiss();
//                            launchActivity(new Intent(TalkActivity.this, FocusChannelActivity.class));
                        }
                    });
                    shareImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popWnd.dismiss();
//                            launchActivity(new Intent(TalkActivity.this, FocusChannelActivity.class));
                        }
                    });
                    popWnd.setContentView(contentView);
                    popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                    popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    popWnd.setBackgroundDrawable(null);
                    WindowManager.LayoutParams lp = TalkActivity.this.getWindow()
                            .getAttributes();
                    lp.alpha = 0.5f;
                    TalkActivity.this.getWindow().setAttributes(lp);
                    popwindow = true;
                    popWnd.setBackgroundDrawable(new BitmapDrawable());
                    popWnd.setFocusable(true);
                    popWnd.showAtLocation(v, Gravity.TOP, 220, 165);

                } else {
                    popWnd.dismiss();
                }
            }
        });
    }


    public int getCidType() {
        return cidType;
    }


    public static TalkActivity getTalkActivity() {
        return talkActivity;
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
    public void onBackPressed() {
        LoginActivity.getLoginActivity().sessionApi.sessionBye(LoginActivity.getLoginActivity().uid, HolderMessage.channel.cid.getType(), HolderMessage.channel.cid.getId());
        super.onBackPressed();
    }

    @OnClick({R.id.mytoolbar_back, R.id.mytoolbar_share, R.id.mytoolbar_other, R.id.imageView9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mytoolbar_back:
                LoginActivity.getLoginActivity().sessionApi.sessionBye(LoginActivity.getLoginActivity().uid, HolderMessage.channel.cid.getType(), HolderMessage.channel.cid.getId());
                finish();
                break;
            case R.id.mytoolbar_share:
                break;
            case R.id.mytoolbar_other:
                LoginActivity.getLoginActivity().channelApi.channelMemberGet(
                        LoginActivity.getLoginActivity().uid, cidType, cidId
                );
                break;
            case R.id.imageView9:
                break;
        }
    }

    @Override
    public void onMediaInitializedEnd(int i, int i1, int i2) {

    }

    @Override
    public void onPttButtonPressed(int i, int i1) {

    }

    @Override
    public void onTalkRequestConfirm(int i, int i1, int i2, int i3, boolean b) {

    }

    @Override
    public void onTalkRequestDeny(int i, int i1, int i2) {

    }

    @Override
    public void onTalkRequestQueued(int i, int i1, int i2) {

    }

    @Override
    public void onTalkReleaseConfirm(int i, int i1) {

    }

    @Override
    public void onTalkTransmitBroken(int i, int i1) {

    }

    @Override
    public void onStartPlaying(int i, int i1, int i2, int i3) {

    }

    @Override
    public void onPlayStopped(int i) {

    }

    @Override
    public void onSomeoneSpeaking(int i, int i1, int i2, int i3, int i4) {
        if (mPresenter.isSpots() == 1) {
            LoginActivity.getLoginActivity().sessionApi.talkRelease(
                    LoginActivity.getLoginActivity().uid, HolderMessage.channel.cid.getType(), HolderMessage.channel.cid.getId()
            );
            AlertDialog alertDialog = new AlertDialog.Builder(TalkActivity.this).create();
            alertDialog.show();
            WindowManager.LayoutParams p = alertDialog.getWindow().getAttributes();
            p.height = (int) (UiUtils.getScreenHeidth(getApplicationContext()) * 0.2);
            p.width = (int) (UiUtils.getScreenWidth(getApplicationContext()) * 0.65);
            Window window = alertDialog.getWindow();
            window.setAttributes(p);
            window.setContentView(R.layout.spots_dialog);
            window.findViewById(R.id.confirm).setOnClickListener((v) -> alertDialog.dismiss());
        }
    }

    @Override
    public void onThatoneSayOver(int i, int i1) {
        if (mPresenter.isSpots() == 0) {
            AlertDialog alertDialog = new AlertDialog.Builder(TalkActivity.this).create();
            alertDialog.show();
            WindowManager.LayoutParams p = alertDialog.getWindow().getAttributes();
            p.height = (int) (UiUtils.getScreenHeidth(getApplicationContext()) * 0.2);
            p.width = (int) (UiUtils.getScreenWidth(getApplicationContext()) * 0.65);
            Window window = alertDialog.getWindow();
            window.setAttributes(p);
            window.setContentView(R.layout.spots_finish_dialog);
            window.findViewById(R.id.confirm).setOnClickListener((v) -> alertDialog.dismiss());
        }
    }

    @Override
    public void onSomeoneAttempt(int i, int i1, int i2) {

    }

    @Override
    public void onThatAttemptQuit(int i, int i1, int i2) {

    }

    @Override
    public void onNewSpeakingCatched(HistoryRecord historyRecord) {

        talkHistory.dumpSpeechBuffer(historyRecord.played, historyRecord.session, historyRecord.owner, historyRecord.tag,
                historyRecord.mediaData, historyRecord.mediaData.length);
        HistoryRecord[] hisRecs = talkHistory.getAllHistoryRecords(compactID.getCompactId());

        hisRecs[0].mediaData = new byte[hisRecs[0].duration / 2 * AMR475_PL_SIZE];
        talkHistory.readSpeechBuffer(compactID.getCompactId(), 1, hisRecs[0].mediaData, 0);
        historyRecords.add(hisRecs[0]);
        msgAdapter.notifyDataSetChanged();
        msgRecyclerView.setAdapter(msgAdapter);
    }

    @Override
    public void onPlayLastSpeaking(int i, int i1) {

    }

    @Override
    public void onPlayLastSpeakingEnd(int i) {

    }

    @Override
    public void onMediaSenderCutted(int i, int i1) {

    }

    @Override
    public void onMediaSenderReport(long l, int i, int i1, int i2, int i3) {


    }

    @Override
    public void onMediaReceiverReport(long l, int i, int i1, int i2, int i3) {

    }

    @Override
    public void onRecorderMeter(int i, int i1) {

    }

    @Override
    public void onPlayerMeter(int i, int i1) {

    }

    @Override
    public void onBluetoothBatteryGet(int i) {

    }

    @Override
    public void onBluetoothConnect(int i) {

    }

    @Override
    public int getSpots() {
        return spots;
    }

    @Override
    public int getChannelId() {
        return cidId;
    }
}

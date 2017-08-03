package com.drop.ttb.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algebra.sdk.API;
import com.algebra.sdk.ChannelApi;
import com.algebra.sdk.OnChannelListener;
import com.algebra.sdk.OnSessionListener;
import com.algebra.sdk.entity.Channel;
import com.algebra.sdk.entity.Contact;
import com.drop.ttb.R;
import com.drop.ttb.di.component.DaggerMessageComponent;
import com.drop.ttb.di.module.MessageModule;
import com.drop.ttb.mvp.contract.MessageContract;
import com.drop.ttb.mvp.presenter.MessagePresenter;
import com.drop.ttb.mvp.ui.activity.LoginActivity;
import com.drop.ttb.mvp.ui.activity.TalkActivity;
import com.drop.ttb.mvp.ui.adapter.AdapterMessage;
import com.drop.ttb.mvp.ui.holder.HolderMessage;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MessageFragment extends BaseFragment<MessagePresenter> implements MessageContract.View, OnChannelListener, OnSessionListener {


    @BindView(R.id.recyclerview_week)
    RecyclerView recyclerviewWeek;
    static MessageFragment fragment;

    public List<Channel> list = new ArrayList<>();


    private Handler uiHandler = null;
    AdapterMessage adapterMessage = new AdapterMessage(list);
    public ChannelApi channelApi = null;

    public static MessageFragment newInstance() {
        if (fragment == null) {
            fragment = new MessageFragment();
        }

        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMessageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .messageModule(new MessageModule(this))
                .build()
                .inject(this);

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {


    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

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

    }

    @Override
    public void onDefaultChannelSet(int i, int i1, int i2) {

    }

    @Override
    public void onAdverChannelsGet(int i, Channel channel, List<Channel> list) {
        this.list.addAll(list);
        recyclerviewWeek.setAdapter(adapterMessage);
        UiUtils.configRecycleView(recyclerviewWeek, new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onChannelListGet(int i, Channel channel, List<Channel> list) {
        this.list.addAll(list);
        adapterMessage.notifyDataSetChanged();
        recyclerviewWeek.setAdapter(adapterMessage);
    }

    @Override
    public void onChannelMemberListGet(int i, int i1, int i2, List<Contact> list) {

    }

    @Override
    public void onChannelNameChanged(int i, int i1, int i2, String s) {

    }

    @Override
    public void onChannelAdded(int i, int i1, int i2, String s) {

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

    }

    @Override
    public void onPubChannelSearchResult(int i, List<Channel> list) {

    }

    @Override
    public void onPubChannelFocusResult(int i, int i1) {
    }

    @Override
    public void onPubChannelUnfocusResult(int i, int i1) {

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
        launchActivity(new Intent(getActivity(), TalkActivity.class));
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
}

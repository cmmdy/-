package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.AddNewNoticeBean;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.PostNewChannelNoticeBean;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.AddChannelNoticeContract;

import io.reactivex.Observable;
import retrofit2.http.Body;


@ActivityScope
public class AddChannelNoticeModel extends BaseModel implements AddChannelNoticeContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AddChannelNoticeModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<AddNewNoticeBean> addNewChannelNotice(String noticedata, int channelid, int channeltype, int creatid) {
        Observable<AddNewNoticeBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .addNewNotice(new PostNewChannelNoticeBean(noticedata, channelid, channeltype, creatid));
        return observable;
    }
}
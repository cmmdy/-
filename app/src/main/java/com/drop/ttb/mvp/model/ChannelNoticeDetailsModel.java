package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.DeleteMessageBean;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.PostNoticeChannelIdBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.ChannelNoticeDetailsContract;

import io.reactivex.Observable;


@ActivityScope
public class ChannelNoticeDetailsModel extends BaseModel implements ChannelNoticeDetailsContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ChannelNoticeDetailsModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<DeleteMessageBean> deleteNotice(int id) {
        Observable<DeleteMessageBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .deleteNotice(new PostChannelId(id));
        return observable;
    }
}
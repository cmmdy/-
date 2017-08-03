package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.PostNoticeChannelIdBean;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.ChannelNoticeContract;

import io.reactivex.Observable;


@ActivityScope
public class ChannelNoticeModel extends BaseModel implements ChannelNoticeContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ChannelNoticeModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<GetAllNoticeBean> getChannelNotice(int id) {
        Observable<GetAllNoticeBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .getChannelNotice(new PostNoticeChannelIdBean(id));
        return observable;
    }
}
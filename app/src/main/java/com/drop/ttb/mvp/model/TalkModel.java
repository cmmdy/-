package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.PostChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.TalkContract;

import io.reactivex.Observable;


@ActivityScope
public class TalkModel extends BaseModel implements TalkContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public TalkModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<ResultCodeBean> updataChannel(int id, String name, String channelimg, int channeltype, int guideid, int guidestatus) {
        Observable<ResultCodeBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .updataChannel(new PostChannelAddNewChannelBean(id, name, channelimg, channeltype, guideid, guidestatus));
        return observable;
    }

    @Override
    public Observable<ChannelAddNewChannelBean> getChannel(int id) {
        Observable<ChannelAddNewChannelBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .getChannel(new PostChannelId(id));
        return observable;
    }
}
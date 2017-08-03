package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.DeleteMessageBean;
import com.drop.ttb.mvp.model.entity.GetMyMessageBean;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.PostDeleteMessageBean;
import com.drop.ttb.mvp.model.entity.PostMyMessageBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.MyMessageContract;

import io.reactivex.Observable;


@ActivityScope
public class MyMessageModel extends BaseModel implements MyMessageContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MyMessageModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<GetMyMessageBean> getMyMessage(int id) {
        Observable<GetMyMessageBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .getMyMessage(new PostMyMessageBean(id));
        return observable;
    }

    @Override
    public Observable<DeleteMessageBean> deleteMessage(int id) {
        Observable<DeleteMessageBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .deleteMessage(new PostDeleteMessageBean(id));
        return observable;
    }
}
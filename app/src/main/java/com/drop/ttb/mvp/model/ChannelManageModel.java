package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.AddNewDynamic;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.model.entity.GetChannelDynamic;
import com.drop.ttb.mvp.model.entity.PostAddNewDynamic;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.PostGetChannelDynamic;
import com.drop.ttb.mvp.model.entity.PostNoticeChannelIdBean;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.ChannelManageContract;

import io.reactivex.Observable;
import retrofit2.http.POST;


@ActivityScope
public class ChannelManageModel extends BaseModel implements ChannelManageContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ChannelManageModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<ResultCodeBean> deleteChannel(int id) {
        Observable<ResultCodeBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .deleteChannel(new PostChannelId(id));
        return observable;
    }

    @Override
    public Observable<GetChannelDynamic> getChannelDynamic(int channelid) {
        Observable<GetChannelDynamic> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .getChannelDynamic(new PostGetChannelDynamic(channelid));
        return observable;
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
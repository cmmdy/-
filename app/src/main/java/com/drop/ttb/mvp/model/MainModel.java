package com.drop.ttb.mvp.model;

import android.app.Application;
import android.util.Log;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.PostChannelAddNewChannelBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.MainContract;

import io.reactivex.Observable;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<ChannelAddNewChannelBean> getChannelList(int id, String name, String channelimg, int channeltype, int guideid, int guidestatus) {
        Observable<ChannelAddNewChannelBean> observable =
                mRepositoryManager
                .obtainRetrofitService(TalkService.class)
                        .addNewChannel(new PostChannelAddNewChannelBean(id, name, channelimg, channeltype, guideid, guidestatus));
        return observable;
    }
}
package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.AddNewDynamic;
import com.drop.ttb.mvp.model.entity.AddNewMessageBean;
import com.drop.ttb.mvp.model.entity.PostAddNewDynamic;
import com.drop.ttb.mvp.model.entity.PostAddNewMessageBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.AddNewMessageContract;

import io.reactivex.Observable;


@ActivityScope
public class AddNewMessageModel extends BaseModel implements AddNewMessageContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AddNewMessageModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<AddNewMessageBean> addNewMessage(String voicedata, String textdata, int communicationstatus, int accepterid, int creatid, int channelid, int channeltype) {
        Observable<AddNewMessageBean> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .addNewMessage(new PostAddNewMessageBean(voicedata, textdata, communicationstatus, accepterid, creatid, channelid, channeltype));
        return observable;
    }
}
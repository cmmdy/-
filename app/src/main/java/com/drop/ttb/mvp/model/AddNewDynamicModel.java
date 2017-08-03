package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.AddNewDynamic;
import com.drop.ttb.mvp.model.entity.PostAddNewDynamic;
import com.drop.ttb.mvp.model.entity.PostChannelId;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.AddNewDynamicContract;

import io.reactivex.Observable;


@ActivityScope
public class AddNewDynamicModel extends BaseModel implements AddNewDynamicContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AddNewDynamicModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<AddNewDynamic> addNewDynamic(String dynamicidata, String dynamicimg, int favornum, int channelid, int creatid, int channeltype) {
        Observable<AddNewDynamic> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .addNewDynamic(new PostAddNewDynamic(dynamicidata, dynamicimg, favornum, channelid, creatid, channeltype));
        return observable;
    }
}
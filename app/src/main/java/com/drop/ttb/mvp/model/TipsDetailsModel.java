package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.entity.AddNewComment;
import com.drop.ttb.mvp.model.entity.GetComment;
import com.drop.ttb.mvp.model.entity.PostGetComment;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.TipsDetailsContract;

import io.reactivex.Observable;
import retrofit2.http.Body;


@ActivityScope
public class TipsDetailsModel extends BaseModel implements TipsDetailsContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public TipsDetailsModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<AddNewComment> addNewComment(String commentdata, int dynamicid, int creatid) {
        return null;
    }

    @Override
    public Observable<GetComment> updataDynamic(@Body PostGetComment postGetComment) {
        return null;
    }
}
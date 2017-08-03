package com.drop.ttb.mvp.model;

import android.app.Application;

import com.drop.ttb.mvp.model.entity.AddNewDynamic;
import com.drop.ttb.mvp.model.entity.GetChannelDynamic;
import com.drop.ttb.mvp.model.api.service.TalkService;
import com.drop.ttb.mvp.model.entity.AddNewComment;
import com.drop.ttb.mvp.model.entity.GetComment;
import com.drop.ttb.mvp.model.entity.PostAddNewComment;
import com.drop.ttb.mvp.model.entity.PostAddNewDynamic;
import com.drop.ttb.mvp.model.entity.PostGetChannelDynamic;
import com.drop.ttb.mvp.model.entity.PostGetComment;
import com.drop.ttb.mvp.model.entity.PostUpdataDynamic;
import com.drop.ttb.mvp.model.entity.PostUserDynamicBean;
import com.drop.ttb.mvp.model.entity.UpdataDynamic;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.TipsContract;

import io.reactivex.Observable;
import retrofit2.http.Body;


@ActivityScope
public class TipsModel extends BaseModel implements TipsContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public TipsModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<GetChannelDynamic> getChannelDynamic(int channelid) {
        Observable<GetChannelDynamic> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .getChannelDynamic(new PostGetChannelDynamic(channelid));
        return observable;
    }

    @Override
    public Observable<AddNewComment> addNewComment(String commentdata, int dynamicid, int creatid) {
        Observable<AddNewComment> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .addNewComment(new PostAddNewComment(commentdata, dynamicid, creatid));
        return observable;
    }

    @Override
    public Observable<GetComment> getComment(int dynamicid) {
        Observable<GetComment> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .getComment(new PostGetComment(dynamicid));
        return observable;
    }

    @Override
    public Observable<UpdataDynamic> updataDynamic(int id, String dynamicidata, String dynamicimg, int favornum, int channelid, int creatid, int channeltype) {
        Observable<UpdataDynamic> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .updataDynamic(new PostUpdataDynamic(id, dynamicidata, dynamicimg, favornum, channelid, creatid, channeltype));
        return observable;
    }

    @Override
    public Observable<GetChannelDynamic> getUserDynamic(int creatid) {
        Observable<GetChannelDynamic> observable =
                mRepositoryManager
                        .obtainRetrofitService(TalkService.class)
                        .getUserDynamic(new PostUserDynamicBean(creatid));
        return observable;
    }

}
package com.drop.ttb.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.MainContract;


@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void setData(){
        for(int i=0; i<mRootView.getChannelList().size(); i++){
            mModel.getChannelList(mRootView.getChannelList().get(i).cid.getId(),
                    mRootView.getChannelList().get(i).name,
                    "nono",
                    mRootView.getChannelList().get(i).cid.getType(),
                    mRootView.getChannelList().get(i).owner.i,
                    0).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxUtils.bindToLifecycle(mRootView))
            .subscribe(new ErrorHandleSubscriber<ChannelAddNewChannelBean>(mErrorHandler) {
                @Override
                public void onNext(@NonNull ChannelAddNewChannelBean channelAddNewChannelBean) {
                    Log.i("nono1", channelAddNewChannelBean.toString());
                }
            });

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}
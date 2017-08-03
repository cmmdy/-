package com.drop.ttb.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.AddNewNoticeBean;
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

import com.drop.ttb.mvp.contract.AddChannelNoticeContract;


@ActivityScope
public class AddChannelNoticePresenter extends BasePresenter<AddChannelNoticeContract.Model, AddChannelNoticeContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public AddChannelNoticePresenter(AddChannelNoticeContract.Model model, AddChannelNoticeContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void setNotice(String noticedata, int channelid, int channeltype, int creatid){
        mModel.addNewChannelNotice(noticedata, channelid, channeltype, creatid)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<AddNewNoticeBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull AddNewNoticeBean addNewNoticeBean) {
                        Log.e("addnew", "success");
                        mRootView.killMyself();
                    }
                });
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
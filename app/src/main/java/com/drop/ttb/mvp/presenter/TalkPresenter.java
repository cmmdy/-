package com.drop.ttb.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
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

import com.drop.ttb.mvp.contract.TalkContract;


@ActivityScope
public class TalkPresenter extends BasePresenter<TalkContract.Model, TalkContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public TalkPresenter(TalkContract.Model model, TalkContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void setData() {
        mModel.getChannel(mRootView.getChannelId())
                .observeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<ChannelAddNewChannelBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ChannelAddNewChannelBean channelAddNewChannelBean) {
                        mModel.updataChannel(
                                channelAddNewChannelBean.getResultData().getId(),
                                channelAddNewChannelBean.getResultData().getName(),
                                channelAddNewChannelBean.getResultData().getChannelimg(),
                                channelAddNewChannelBean.getResultData().getChanneltype(),
                                channelAddNewChannelBean.getResultData().getGuideid(),
                                mRootView.getSpots())
                                .observeOn(Schedulers.io())
                                .compose(RxUtils.bindToLifecycle(mRootView))
                                .subscribeOn(AndroidSchedulers.mainThread())
                                .subscribe(new ErrorHandleSubscriber<ResultCodeBean>(mErrorHandler) {
                                    @Override
                                    public void onNext(@NonNull ResultCodeBean resultCodeBean) {
                                        Log.e("--i", "spots success");
                                    }
                                });

                    }
                });
    }

    public int isSpots() {
        final int[] i = {0};
        mModel.getChannel(mRootView.getChannelId())
                .observeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<ChannelAddNewChannelBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ChannelAddNewChannelBean channelAddNewChannelBean) {
                        i[0] = channelAddNewChannelBean.getResultData().getGuidestatus();
                    }
                });
        return i[0];
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
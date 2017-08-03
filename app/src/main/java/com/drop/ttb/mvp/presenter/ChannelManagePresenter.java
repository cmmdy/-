package com.drop.ttb.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.drop.ttb.R;
import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.DynamicBean;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.model.entity.GetChannelDynamic;
import com.drop.ttb.mvp.model.entity.ResultCodeBean;
import com.drop.ttb.mvp.ui.activity.ChannelManageActivity;
import com.drop.ttb.mvp.ui.activity.TalkActivity;
import com.drop.ttb.mvp.ui.adapter.AdapterNotice;
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

import com.drop.ttb.mvp.contract.ChannelManageContract;


@ActivityScope
public class ChannelManagePresenter extends BasePresenter<ChannelManageContract.Model, ChannelManageContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ChannelManagePresenter(ChannelManageContract.Model model, ChannelManageContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void deleteChannel(){
        mModel.deleteChannel(mRootView.getId())
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<ResultCodeBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull ResultCodeBean resultCodeBean) {
                        Log.e("--s", "delete success");
                    }
                });
    }

    public void getDynamic(){
        mModel.getChannelDynamic(TalkActivity.getTalkActivity().getChannelId())
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<GetChannelDynamic>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GetChannelDynamic getChannelDynamic) {
                        if(getChannelDynamic.getResultData().size()>0) {
//                            mRootView.setDynamic(getChannelDynamic.getResultData().get(0).getDynamicdata());
                        }
                    }
                });
    }

    public void requestNotice(){

        mModel.getChannelNotice(ChannelManageActivity.getChannelManageActivity().getId())
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<GetAllNoticeBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GetAllNoticeBean getAllNoticeBean) {
                        if(getAllNoticeBean.getResultData().size()>0) {
//                            mRootView.setNotice(getAllNoticeBean.getResultData().get(0).getNoticedata());
                        }
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
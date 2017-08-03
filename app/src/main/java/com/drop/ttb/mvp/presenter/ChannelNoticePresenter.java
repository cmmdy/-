package com.drop.ttb.mvp.presenter;

import android.app.Application;

import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.GetAllNoticeBean;
import com.drop.ttb.mvp.ui.activity.ChannelManageActivity;
import com.drop.ttb.mvp.ui.adapter.AdapterNotice;
import com.jess.arms.base.DefaultAdapter;
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

import com.drop.ttb.mvp.contract.ChannelNoticeContract;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


@ActivityScope
public class ChannelNoticePresenter extends BasePresenter<ChannelNoticeContract.Model, ChannelNoticeContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private List<GetAllNoticeBean.ResultDataBean> resultDataBeen = new ArrayList<>();
    private DefaultAdapter mAdapter;

    @Inject
    public ChannelNoticePresenter(ChannelNoticeContract.Model model, ChannelNoticeContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void requestNotice(){
        if(mAdapter == null){
            mAdapter = new AdapterNotice(resultDataBeen);
            mRootView.setAdapter(mAdapter);
        }

        mModel.getChannelNotice(ChannelManageActivity.getChannelManageActivity().getId())
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<GetAllNoticeBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GetAllNoticeBean getAllNoticeBean) {
                        resultDataBeen.clear();
                        resultDataBeen.addAll(getAllNoticeBean.getResultData());
                        mAdapter.notifyDataSetChanged();
                        mRootView.setAdapter(mAdapter);
                        if(resultDataBeen.size() != 0){
                            mRootView.showno(GONE);
                        } else {
                            mRootView.showno(VISIBLE);
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
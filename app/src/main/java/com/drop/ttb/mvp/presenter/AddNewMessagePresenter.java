package com.drop.ttb.mvp.presenter;

import android.app.Application;

import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.AddNewMessageBean;
import com.drop.ttb.mvp.ui.activity.LoginActivity;
import com.drop.ttb.mvp.ui.activity.TalkActivity;
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

import com.drop.ttb.mvp.contract.AddNewMessageContract;


@ActivityScope
public class AddNewMessagePresenter extends BasePresenter<AddNewMessageContract.Model, AddNewMessageContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public AddNewMessagePresenter(AddNewMessageContract.Model model, AddNewMessageContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void addNewMessage(int id, boolean b){
        mModel.addNewMessage("", mRootView.getText(), 0, id, LoginActivity.getLoginActivity().uid, TalkActivity.getTalkActivity().getChannelId(), TalkActivity.getTalkActivity().getCidType())
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<AddNewMessageBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull AddNewMessageBean addNewMessageBean) {
                        if(b){
                            mRootView.killMyself();
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
package com.drop.ttb.mvp.presenter;

import android.app.Application;

import com.drop.ttb.R;
import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.DeleteMessageBean;
import com.drop.ttb.mvp.model.entity.GetMyMessageBean;
import com.drop.ttb.mvp.ui.activity.LoginActivity;
import com.drop.ttb.mvp.ui.activity.MainActivity;
import com.drop.ttb.mvp.ui.adapter.AdapterMyMessage;
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

import com.drop.ttb.mvp.contract.MyMessageContract;

import java.util.ArrayList;
import java.util.List;


@ActivityScope
public class MyMessagePresenter extends BasePresenter<MyMessageContract.Model, MyMessageContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private List<GetMyMessageBean.ResultDataBean> resultDataBeen = new ArrayList<>();
    private AdapterMyMessage adapterMyMessage;

    @Inject
    public MyMessagePresenter(MyMessageContract.Model model, MyMessageContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void getMyMessage(){
        if(adapterMyMessage == null){
            adapterMyMessage = new AdapterMyMessage(mAppManager.getCurrentActivity(), R.layout.messageitem, resultDataBeen);
            mRootView.setAdapter(adapterMyMessage);
        }
        mModel.getMyMessage(LoginActivity.getLoginActivity().uid)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<GetMyMessageBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GetMyMessageBean getMyMessageBean) {
                        for(int i=0;i <getMyMessageBean.getResultData().size(); i++){
                            for(int j=0; j<MainActivity.getMainActivity().getChannelList().size(); j++){
                                if(getMyMessageBean.getResultData().get(i).getChannelid() == MainActivity.getMainActivity().getChannelList().get(j).cid.getId()){
                                    getMyMessageBean.getResultData().get(i).setChannelname(MainActivity.getMainActivity().getChannelList().get(j).name);
                                }
                            }
                        }
                        resultDataBeen.addAll(getMyMessageBean.getResultData());
                        adapterMyMessage.notifyDataSetChanged();
                        if(resultDataBeen.size() != 0){
                            mRootView.setno();
                        }
                        mRootView.setAdapter(adapterMyMessage);
                    }
                });
    }

    public void onClick(int position){
        deleteMessage(resultDataBeen.get(position).getId());
        resultDataBeen.remove(position);
        adapterMyMessage.notifyDataSetChanged();
        if(resultDataBeen.size() == 0){
            mRootView.setno();
        }
        mRootView.setAdapter(adapterMyMessage);
    }

    public void deleteMessage(int id){
        mModel.deleteMessage(id)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<DeleteMessageBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull DeleteMessageBean deleteMessageBean) {

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
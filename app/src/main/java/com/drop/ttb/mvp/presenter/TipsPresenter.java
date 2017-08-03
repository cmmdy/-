package com.drop.ttb.mvp.presenter;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.drop.ttb.R;
import com.drop.ttb.app.utils.RxUtils;
import com.drop.ttb.mvp.model.entity.GetChannelDynamic;
import com.drop.ttb.mvp.model.entity.AddNewComment;
import com.drop.ttb.mvp.model.entity.DynamicBean;
import com.drop.ttb.mvp.model.entity.GetComment;
import com.drop.ttb.mvp.model.entity.PostUpdataDynamic;
import com.drop.ttb.mvp.model.entity.UpdataDynamic;
import com.drop.ttb.mvp.ui.activity.LoginActivity;
import com.drop.ttb.mvp.ui.activity.TalkActivity;
import com.drop.ttb.mvp.ui.adapter.AdapterDynamic;
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

import com.drop.ttb.mvp.contract.TipsContract;

import java.util.ArrayList;
import java.util.List;

import static com.amap.api.mapcore.util.cz.G;
import static com.amap.api.mapcore.util.cz.P;


@ActivityScope
public class TipsPresenter extends BasePresenter<TipsContract.Model, TipsContract.View>
        implements DefaultAdapter.OnRecyclerViewItemClickListener {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private DefaultAdapter adapterDynamic;

    private List<DynamicBean> dynamicBeen = new ArrayList<>();

    @Inject
    public TipsPresenter(TipsContract.Model model, TipsContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;

    }

    public void getChannelDynamic() {


        if (adapterDynamic == null) {
            adapterDynamic = new AdapterDynamic(dynamicBeen, mRootView.getB());
        }
        adapterDynamic.setOnItemClickListener(this);
        try {
            mModel.getChannelDynamic(TalkActivity.getTalkActivity().getChannelId())
                    .subscribeOn(Schedulers.io())
                    .compose(RxUtils.bindToLifecycle(mRootView))
                    .observeOn(Schedulers.io())
                    .subscribe(new ErrorHandleSubscriber<GetChannelDynamic>(mErrorHandler) {
                        @Override
                        public void onNext(@NonNull GetChannelDynamic getChannelDynamic) {
                            dynamicBeen.clear();
                            for (int i = 0; i < getChannelDynamic.getResultData().size(); i++) {
                                GetChannelDynamic.ResultDataBean temp = getChannelDynamic.getResultData().get(i);
                                DynamicBean dynamicBean = new DynamicBean();
                                dynamicBeen.add(dynamicBean);
                                dynamicBeen.get(i).setId(temp.getId());
                                dynamicBeen.get(i).setChannelid(temp.getChannelid());
                                dynamicBeen.get(i).setName(String.valueOf(temp.getCreatid()));
                                dynamicBeen.get(i).setImage(R.drawable.left_head);
                                dynamicBeen.get(i).setTime(temp.getCreatdate());
                                dynamicBeen.get(i).setText(temp.getDynamicdata());
                                dynamicBeen.get(i).setCount(temp.getFavornum());
                            }
                        }
                    });
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void getUserDynamic(int id){
        if (adapterDynamic == null) {
            adapterDynamic = new AdapterDynamic(dynamicBeen, mRootView.getB());
        }
        adapterDynamic.setOnItemClickListener(this);

        mModel.getUserDynamic(id)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(Schedulers.io())
                .subscribe(new ErrorHandleSubscriber<GetChannelDynamic>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GetChannelDynamic getChannelDynamic) {
                        dynamicBeen.clear();
                        for (int i = 0; i < getChannelDynamic.getResultData().size(); i++) {
                            GetChannelDynamic.ResultDataBean temp = getChannelDynamic.getResultData().get(i);
                            DynamicBean dynamicBean = new DynamicBean();
                            dynamicBeen.add(dynamicBean);
                            dynamicBeen.get(i).setId(temp.getId());
                            dynamicBeen.get(i).setChannelid(temp.getChannelid());
                            dynamicBeen.get(i).setName(String.valueOf(temp.getCreatid()));
                            dynamicBeen.get(i).setImage(R.drawable.left_head);
                            dynamicBeen.get(i).setTime(temp.getCreatdate());
                            dynamicBeen.get(i).setText(temp.getDynamicdata());
                            dynamicBeen.get(i).setCount(temp.getFavornum());
                        }
                    }
                });
    }

    public void getComment() {
        mModel.getComment(1)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<GetComment>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GetComment getComment) {
                        for (int k = 0; k < dynamicBeen.size(); k++) {
                            for (int i = 0; i < getComment.getResultData().size(); i++) {
                                if (dynamicBeen.get(k).getId() == getComment.getResultData().get(i).getDynamicid()) {
                                    if (dynamicBeen.get(k).getComment().size() != 0) {
                                        for (int j = 0; j < dynamicBeen.get(k).getComment().size(); j++) {
                                            if (dynamicBeen.get(k).getComment().get(j).equals(getComment.getResultData().get(i).getCommentdata())) {
                                                break;
                                            }
                                            if (j + 1 == dynamicBeen.get(k).getComment().size()) {
                                                dynamicBeen.get(k).setComment(getComment.getResultData().get(i).getCommentdata());
                                            }
                                        }
                                    } else {
                                        dynamicBeen.get(k).setComment(getComment.getResultData().get(i).getCommentdata());
                                    }
//                                    dynamicBeen.get(k).setComment(getComment.getResultData().get(i).getCommentdata());
                                }
                            }
                        }
                        adapterDynamic.notifyDataSetChanged();
                        mRootView.setDynamicAdapter(adapterDynamic);
                    }
                });
    }

    public void addNewComment(String commentdata, int dynamicid, int creatid, int position) {
        mModel.addNewComment(commentdata, dynamicid, creatid)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<AddNewComment>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull AddNewComment addNewComment) {
                        dynamicBeen.get(position).getComment().add(addNewComment.getResultData().getCommentdata());
                        adapterDynamic.notifyDataSetChanged();
                        mRootView.setDynamicAdapter(adapterDynamic);
                    }
                });
    }

    public void updataDynamic(int id, String s1, String s2, int i3) {
        mModel.updataDynamic(id, s1, s2, i3, TalkActivity.getTalkActivity().getChannelId(), LoginActivity.getLoginActivity().uid, TalkActivity.getTalkActivity().getCidType())
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<UpdataDynamic>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull UpdataDynamic updataDynamic) {

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

    @Override
    public void onItemClick(View view, int viewType, Object data, int position) {
        TextView comment = (TextView) view.findViewById(R.id.comment);
        EditText commenttext = (EditText) view.findViewById(R.id.commenttext);
        ImageView iv = (ImageView) view.findViewById(R.id.like);
        TextView count = (TextView) view.findViewById(R.id.count);
        DynamicBean temp = (DynamicBean) data;
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!commenttext.getText().toString().equals("")) {
                    String commentdata = LoginActivity.getLoginActivity().uid + ":" + commenttext.getText().toString();
                    int dynamicid = temp.getId();
                    int creatid = temp.getCreatid();
                    addNewComment(commentdata, dynamicid, creatid, position);
                }
            }
        });


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count.setText(temp.getCount() + 1 + "");
                updataDynamic(temp.getId(), temp.getText(), "0", temp.getCount() + 1);
                iv.setImageResource(R.drawable.like_click);
            }
        });
    }

}
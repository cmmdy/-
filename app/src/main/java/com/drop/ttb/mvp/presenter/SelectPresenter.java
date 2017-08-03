package com.drop.ttb.mvp.presenter;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.drop.ttb.R;
import com.drop.ttb.mvp.model.entity.SelectBean;
import com.drop.ttb.mvp.ui.activity.LoginActivity;
import com.drop.ttb.mvp.ui.adapter.AdapterSelect;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.drop.ttb.mvp.contract.SelectContract;

import org.apache.commons.logging.Log;

import java.util.ArrayList;
import java.util.List;

import static com.amap.api.mapcore.util.cz.A;
import static com.amap.api.mapcore.util.cz.e;
import static com.amap.api.mapcore.util.cz.s;
import static com.amap.api.mapcore.util.cz.v;
import static com.drop.ttb.R.id.imageView;
import static com.drop.ttb.R.id.iv;
import static com.drop.ttb.R.id.linearLayout;


@ActivityScope
public class SelectPresenter extends BasePresenter<SelectContract.Model, SelectContract.View>
        implements DefaultAdapter.OnRecyclerViewItemClickListener{
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private DefaultAdapter defaultAdapter;
    private ArrayList<SelectBean> selectBeen = new ArrayList<>();

    @Inject
    public SelectPresenter(SelectContract.Model model, SelectContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void setData(){
        if(defaultAdapter == null){
            defaultAdapter = new AdapterSelect(selectBeen);
        }
        selectBeen.addAll(mRootView.getArrayList());
        for(int i=0; i<selectBeen.size(); i++){
            if (selectBeen.get(i).getId() == LoginActivity.getLoginActivity().uid){
                selectBeen.remove(i);
            }
        }
        defaultAdapter.notifyDataSetChanged();
        defaultAdapter.setOnItemClickListener(this);
        mRootView.setAdapter(defaultAdapter);

    }

    public ArrayList<SelectBean> onclick(){
        ArrayList<SelectBean> arrayList = new ArrayList<>();
        arrayList.addAll(defaultAdapter.getInfos());
        return arrayList;
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
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.ll);
        ImageView imageView = (ImageView) view.findViewById(iv);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((SelectBean) data).isSelect()){
                    imageView.setImageResource(R.drawable.unselect);
                    ((SelectBean) data).setSelect(false);
                } else {
                    imageView.setImageResource(R.drawable.select);
                    ((SelectBean) data).setSelect(true);
                }
            }
        });
    }
}
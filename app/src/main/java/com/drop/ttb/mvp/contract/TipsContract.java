package com.drop.ttb.mvp.contract;

import com.drop.ttb.mvp.model.entity.GetChannelDynamic;
import com.drop.ttb.mvp.model.entity.AddNewComment;
import com.drop.ttb.mvp.model.entity.GetComment;
import com.drop.ttb.mvp.model.entity.PostGetComment;
import com.drop.ttb.mvp.model.entity.PostUpdataDynamic;
import com.drop.ttb.mvp.model.entity.PostUserDynamicBean;
import com.drop.ttb.mvp.model.entity.UpdataDynamic;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;
import retrofit2.http.Body;


public interface TipsContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setDynamicAdapter(DefaultAdapter defaultAdapter);

        boolean getB();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        //根据频道ID返回动态列表
        Observable<GetChannelDynamic> getChannelDynamic(int channelid);

        //添加新评论
        Observable<AddNewComment> addNewComment(String commentdata, int dynamicid, int creatid);

        //根据动态ID返回评论列表
        Observable<GetComment> getComment(int dynamicid);

        //更新动态
        Observable<UpdataDynamic> updataDynamic(int id, String dynamicidata, String dynamicimg, int favornum, int channelid, int creatid, int channeltype);

        //根据创建者ID返回动态列表
        Observable<GetChannelDynamic> getUserDynamic(int creatid);
    }

}

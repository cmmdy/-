package com.drop.ttb.mvp.contract;

import com.drop.ttb.mvp.model.entity.AddNewComment;
import com.drop.ttb.mvp.model.entity.GetComment;
import com.drop.ttb.mvp.model.entity.PostGetComment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;
import retrofit2.http.Body;


public interface TipsDetailsContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setImageAdapter(DefaultAdapter defaultAdapter);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        //添加新评论
        Observable<AddNewComment> addNewComment(String commentdata, int dynamicid, int creatid);

        //更新动态
        Observable<GetComment> updataDynamic(@Body PostGetComment postGetComment);
    }
}

package com.drop.ttb.mvp.contract;

import com.drop.ttb.mvp.model.entity.SelectBean;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.ArrayList;


public interface SelectContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setAdapter(DefaultAdapter defaultAdapter);
        ArrayList<SelectBean> getArrayList();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

    }
}

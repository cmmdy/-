package com.drop.ttb.mvp.contract;

import com.algebra.sdk.entity.Channel;
import com.drop.ttb.mvp.model.entity.ChannelAddNewChannelBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        List<Channel> getChannelList();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<ChannelAddNewChannelBean> getChannelList(int id, String name, String channelimg, int channeltype, int guideid, int guidestatus);
    }
}

package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.ChannelNoticeContract;
import com.drop.ttb.mvp.model.ChannelNoticeModel;


@Module
public class ChannelNoticeModule {
    private ChannelNoticeContract.View view;

    /**
     * 构建ChannelNoticeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ChannelNoticeModule(ChannelNoticeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ChannelNoticeContract.View provideChannelNoticeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ChannelNoticeContract.Model provideChannelNoticeModel(ChannelNoticeModel model) {
        return model;
    }
}
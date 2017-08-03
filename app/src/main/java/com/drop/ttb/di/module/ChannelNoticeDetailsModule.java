package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.ChannelNoticeDetailsContract;
import com.drop.ttb.mvp.model.ChannelNoticeDetailsModel;


@Module
public class ChannelNoticeDetailsModule {
    private ChannelNoticeDetailsContract.View view;

    /**
     * 构建ChannelNoticeDetailsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ChannelNoticeDetailsModule(ChannelNoticeDetailsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ChannelNoticeDetailsContract.View provideChannelNoticeDetailsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ChannelNoticeDetailsContract.Model provideChannelNoticeDetailsModel(ChannelNoticeDetailsModel model) {
        return model;
    }
}
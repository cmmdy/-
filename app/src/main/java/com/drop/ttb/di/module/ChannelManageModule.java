package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.ChannelManageContract;
import com.drop.ttb.mvp.model.ChannelManageModel;


@Module
public class ChannelManageModule {
    private ChannelManageContract.View view;

    /**
     * 构建ChannelManageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ChannelManageModule(ChannelManageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ChannelManageContract.View provideChannelManageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ChannelManageContract.Model provideChannelManageModel(ChannelManageModel model) {
        return model;
    }
}
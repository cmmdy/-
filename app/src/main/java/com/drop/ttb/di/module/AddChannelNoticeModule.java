package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.AddChannelNoticeContract;
import com.drop.ttb.mvp.model.AddChannelNoticeModel;


@Module
public class AddChannelNoticeModule {
    private AddChannelNoticeContract.View view;

    /**
     * 构建AddChannelNoticeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddChannelNoticeModule(AddChannelNoticeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddChannelNoticeContract.View provideAddChannelNoticeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddChannelNoticeContract.Model provideAddChannelNoticeModel(AddChannelNoticeModel model) {
        return model;
    }
}
package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.CreatChannelContract;
import com.drop.ttb.mvp.model.CreatChannelModel;


@Module
public class CreatChannelModule {
    private CreatChannelContract.View view;

    /**
     * 构建CreatChannelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CreatChannelModule(CreatChannelContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CreatChannelContract.View provideCreatChannelView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CreatChannelContract.Model provideCreatChannelModel(CreatChannelModel model) {
        return model;
    }
}
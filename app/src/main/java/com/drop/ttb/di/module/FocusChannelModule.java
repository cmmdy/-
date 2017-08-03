package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.FocusChannelContract;
import com.drop.ttb.mvp.model.FocusChannelModel;


@Module
public class FocusChannelModule {
    private FocusChannelContract.View view;

    /**
     * 构建FocusChannelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FocusChannelModule(FocusChannelContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    FocusChannelContract.View provideFocusChannelView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    FocusChannelContract.Model provideFocusChannelModel(FocusChannelModel model) {
        return model;
    }
}
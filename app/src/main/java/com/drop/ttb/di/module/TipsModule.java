package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.TipsContract;
import com.drop.ttb.mvp.model.TipsModel;


@Module
public class TipsModule {
    private TipsContract.View view;

    /**
     * 构建TipsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TipsModule(TipsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TipsContract.View provideTipsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TipsContract.Model provideTipsModel(TipsModel model) {
        return model;
    }
}
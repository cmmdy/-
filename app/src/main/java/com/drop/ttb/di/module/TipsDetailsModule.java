package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.TipsDetailsContract;
import com.drop.ttb.mvp.model.TipsDetailsModel;


@Module
public class TipsDetailsModule {
    private TipsDetailsContract.View view;

    /**
     * 构建TipsDetailsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TipsDetailsModule(TipsDetailsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TipsDetailsContract.View provideTipsDetailsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TipsDetailsContract.Model provideTipsDetailsModel(TipsDetailsModel model) {
        return model;
    }
}
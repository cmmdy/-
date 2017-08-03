package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.SelectContract;
import com.drop.ttb.mvp.model.SelectModel;


@Module
public class SelectModule {
    private SelectContract.View view;

    /**
     * 构建SelectModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SelectModule(SelectContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SelectContract.View provideSelectView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SelectContract.Model provideSelectModel(SelectModel model) {
        return model;
    }
}
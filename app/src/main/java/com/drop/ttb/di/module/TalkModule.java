package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.TalkContract;
import com.drop.ttb.mvp.model.TalkModel;


@Module
public class TalkModule {
    private TalkContract.View view;

    /**
     * 构建TalkModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TalkModule(TalkContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TalkContract.View provideTalkView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TalkContract.Model provideTalkModel(TalkModel model) {
        return model;
    }
}
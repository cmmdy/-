package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.SetNameContract;
import com.drop.ttb.mvp.model.SetNameModel;


@Module
public class SetNameModule {
    private SetNameContract.View view;

    /**
     * 构建SetNameModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SetNameModule(SetNameContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SetNameContract.View provideSetNameView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SetNameContract.Model provideSetNameModel(SetNameModel model) {
        return model;
    }
}
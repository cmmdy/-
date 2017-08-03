package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.PasswordContract;
import com.drop.ttb.mvp.model.PasswordModel;


@Module
public class PasswordModule {
    private PasswordContract.View view;

    /**
     * 构建PasswordModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PasswordModule(PasswordContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PasswordContract.View providePasswordView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PasswordContract.Model providePasswordModel(PasswordModel model) {
        return model;
    }
}
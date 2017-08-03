package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.FriendsContract;
import com.drop.ttb.mvp.model.FriendsModel;


@Module
public class FriendsModule {
    private FriendsContract.View view;

    /**
     * 构建FriendsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FriendsModule(FriendsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    FriendsContract.View provideFriendsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    FriendsContract.Model provideFriendsModel(FriendsModel model) {
        return model;
    }
}
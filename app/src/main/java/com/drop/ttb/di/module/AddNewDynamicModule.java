package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.AddNewDynamicContract;
import com.drop.ttb.mvp.model.AddNewDynamicModel;


@Module
public class AddNewDynamicModule {
    private AddNewDynamicContract.View view;

    /**
     * 构建AddNewDynamicModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddNewDynamicModule(AddNewDynamicContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddNewDynamicContract.View provideAddNewDynamicView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddNewDynamicContract.Model provideAddNewDynamicModel(AddNewDynamicModel model) {
        return model;
    }
}
package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.AddNewMessageContract;
import com.drop.ttb.mvp.model.AddNewMessageModel;


@Module
public class AddNewMessageModule {
    private AddNewMessageContract.View view;

    /**
     * 构建AddNewMessageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AddNewMessageModule(AddNewMessageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AddNewMessageContract.View provideAddNewMessageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AddNewMessageContract.Model provideAddNewMessageModel(AddNewMessageModel model) {
        return model;
    }
}
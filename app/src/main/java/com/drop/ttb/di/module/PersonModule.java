package com.drop.ttb.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.drop.ttb.mvp.contract.PersonContract;
import com.drop.ttb.mvp.model.PersonModel;


@Module
public class PersonModule {
    private PersonContract.View view;

    /**
     * 构建PersonModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public PersonModule(PersonContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PersonContract.View providePersonView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PersonContract.Model providePersonModel(PersonModel model) {
        return model;
    }
}
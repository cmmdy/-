package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.AddNewDynamicModule;

import com.drop.ttb.mvp.ui.activity.AddNewDynamicActivity;

@ActivityScope
@Component(modules = AddNewDynamicModule.class, dependencies = AppComponent.class)
public interface AddNewDynamicComponent {
    void inject(AddNewDynamicActivity activity);
}
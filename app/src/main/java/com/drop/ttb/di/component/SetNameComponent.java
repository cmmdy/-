package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.SetNameModule;

import com.drop.ttb.mvp.ui.activity.SetNameActivity;

@ActivityScope
@Component(modules = SetNameModule.class, dependencies = AppComponent.class)
public interface SetNameComponent {
    void inject(SetNameActivity activity);
}
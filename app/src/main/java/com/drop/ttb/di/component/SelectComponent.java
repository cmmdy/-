package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.SelectModule;

import com.drop.ttb.mvp.ui.activity.SelectActivity;

@ActivityScope
@Component(modules = SelectModule.class, dependencies = AppComponent.class)
public interface SelectComponent {
    void inject(SelectActivity activity);
}
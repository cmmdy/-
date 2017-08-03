package com.drop.ttb.di.component;

import com.drop.ttb.mvp.ui.activity.DynamicActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.TipsModule;

@ActivityScope
@Component(modules = TipsModule.class, dependencies = AppComponent.class)
public interface TipsComponent {
    void inject(DynamicActivity activity);
}
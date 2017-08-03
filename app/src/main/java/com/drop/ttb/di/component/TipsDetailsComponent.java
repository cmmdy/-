package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.TipsDetailsModule;

import com.drop.ttb.mvp.ui.activity.TipsDetailsActivity;

@ActivityScope
@Component(modules = TipsDetailsModule.class, dependencies = AppComponent.class)
public interface TipsDetailsComponent {
    void inject(TipsDetailsActivity activity);
}
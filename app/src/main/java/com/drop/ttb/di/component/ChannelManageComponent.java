package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.ChannelManageModule;

import com.drop.ttb.mvp.ui.activity.ChannelManageActivity;

@ActivityScope
@Component(modules = ChannelManageModule.class, dependencies = AppComponent.class)
public interface ChannelManageComponent {
    void inject(ChannelManageActivity activity);
}
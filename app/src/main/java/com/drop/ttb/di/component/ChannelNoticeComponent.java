package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.ChannelNoticeModule;

import com.drop.ttb.mvp.ui.activity.ChannelNoticeActivity;

@ActivityScope
@Component(modules = ChannelNoticeModule.class, dependencies = AppComponent.class)
public interface ChannelNoticeComponent {
    void inject(ChannelNoticeActivity activity);
}
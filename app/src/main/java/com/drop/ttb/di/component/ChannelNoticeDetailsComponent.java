package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.ChannelNoticeDetailsModule;

import com.drop.ttb.mvp.ui.activity.ChannelNoticeDetailsActivity;

@ActivityScope
@Component(modules = ChannelNoticeDetailsModule.class, dependencies = AppComponent.class)
public interface ChannelNoticeDetailsComponent {
    void inject(ChannelNoticeDetailsActivity activity);
}
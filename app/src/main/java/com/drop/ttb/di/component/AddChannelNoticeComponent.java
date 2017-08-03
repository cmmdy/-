package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.AddChannelNoticeModule;

import com.drop.ttb.mvp.ui.activity.AddChannelNoticeActivity;

@ActivityScope
@Component(modules = AddChannelNoticeModule.class, dependencies = AppComponent.class)
public interface AddChannelNoticeComponent {
    void inject(AddChannelNoticeActivity activity);
}
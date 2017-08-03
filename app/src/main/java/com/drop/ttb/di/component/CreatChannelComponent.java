package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.CreatChannelModule;

import com.drop.ttb.mvp.ui.activity.CreatChannelActivity;

@ActivityScope
@Component(modules = CreatChannelModule.class, dependencies = AppComponent.class)
public interface CreatChannelComponent {
    void inject(CreatChannelActivity activity);
}
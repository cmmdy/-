package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.FocusChannelModule;

import com.drop.ttb.mvp.ui.activity.FocusChannelActivity;

@ActivityScope
@Component(modules = FocusChannelModule.class, dependencies = AppComponent.class)
public interface FocusChannelComponent {
    void inject(FocusChannelActivity activity);
}
package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.TalkModule;

import com.drop.ttb.mvp.ui.activity.TalkActivity;

@ActivityScope
@Component(modules = TalkModule.class, dependencies = AppComponent.class)
public interface TalkComponent {
    void inject(TalkActivity activity);
}
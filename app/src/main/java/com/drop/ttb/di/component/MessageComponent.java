package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.MessageModule;

import com.drop.ttb.mvp.ui.fragment.MessageFragment;

@ActivityScope
@Component(modules = MessageModule.class, dependencies = AppComponent.class)
public interface MessageComponent {
    void inject(MessageFragment fragment);
}
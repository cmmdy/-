package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.MyMessageModule;

import com.drop.ttb.mvp.ui.activity.MyMessageActivity;

@ActivityScope
@Component(modules = MyMessageModule.class, dependencies = AppComponent.class)
public interface MyMessageComponent {
    void inject(MyMessageActivity activity);
}
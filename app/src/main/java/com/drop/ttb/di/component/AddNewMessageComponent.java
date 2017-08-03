package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.AddNewMessageModule;

import com.drop.ttb.mvp.ui.activity.AddNewMessageActivity;

@ActivityScope
@Component(modules = AddNewMessageModule.class, dependencies = AppComponent.class)
public interface AddNewMessageComponent {
    void inject(AddNewMessageActivity activity);
}
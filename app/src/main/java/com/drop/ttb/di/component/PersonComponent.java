package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.PersonModule;

import com.drop.ttb.mvp.ui.activity.PersonActivity;

@ActivityScope
@Component(modules = PersonModule.class, dependencies = AppComponent.class)
public interface PersonComponent {
    void inject(PersonActivity activity);
}
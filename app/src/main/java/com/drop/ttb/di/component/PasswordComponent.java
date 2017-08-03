package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.PasswordModule;

import com.drop.ttb.mvp.ui.activity.PasswordActivity;

@ActivityScope
@Component(modules = PasswordModule.class, dependencies = AppComponent.class)
public interface PasswordComponent {
    void inject(PasswordActivity activity);
}
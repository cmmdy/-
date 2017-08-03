package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.FriendsModule;

import com.drop.ttb.mvp.ui.fragment.FriendsFragment;

@ActivityScope
@Component(modules = FriendsModule.class, dependencies = AppComponent.class)
public interface FriendsComponent {
    void inject(FriendsFragment fragment);
}
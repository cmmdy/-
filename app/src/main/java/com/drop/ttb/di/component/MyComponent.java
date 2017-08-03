package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.MyModule;

import com.drop.ttb.mvp.ui.fragment.MyFragment;

@ActivityScope
@Component(modules = MyModule.class, dependencies = AppComponent.class)
public interface MyComponent {
    void inject(MyFragment fragment);
}
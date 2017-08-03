package com.drop.ttb.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.drop.ttb.di.module.ContractModule;

import com.drop.ttb.mvp.ui.fragment.ContractFragment;

@ActivityScope
@Component(modules = ContractModule.class, dependencies = AppComponent.class)
public interface ContractComponent {
    void inject(ContractFragment fragment);
}
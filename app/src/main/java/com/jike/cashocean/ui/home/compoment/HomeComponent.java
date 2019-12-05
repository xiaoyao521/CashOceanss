package com.jike.cashocean.ui.home.compoment;

import com.jike.cashocean.ui.home.HomeFragment;
import com.jike.cashocean.ui.home.module.HomeModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}

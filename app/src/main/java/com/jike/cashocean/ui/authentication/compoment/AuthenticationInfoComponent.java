package com.jike.cashocean.ui.authentication.compoment;

import com.jike.cashocean.ui.authentication.AuthenticationActivity;
import com.jike.cashocean.ui.authentication.module.SaveAuthenticationInfoModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SaveAuthenticationInfoModule.class})
public interface AuthenticationInfoComponent {
    void inject(AuthenticationActivity authenticationActivity);
}

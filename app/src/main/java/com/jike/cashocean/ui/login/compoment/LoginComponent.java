package com.jike.cashocean.ui.login.compoment;

import com.jike.cashocean.ui.login.FindPasswordActivity;
import com.jike.cashocean.ui.login.FindPasswordCodeActivity;
import com.jike.cashocean.ui.login.FindPasswordRestActivity;
import com.jike.cashocean.ui.login.LoginActivity;
import com.jike.cashocean.ui.login.LoginPasswordActivity;
import com.jike.cashocean.ui.login.RegisterActivity;
import com.jike.cashocean.ui.login.RegisterCodeActivity;
import com.jike.cashocean.ui.login.RegisterPasswordActivity;
import com.jike.cashocean.ui.login.RegisterPasswordFacebookActivity;
import com.jike.cashocean.ui.login.module.LoginRegisterForgetPasswordModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LoginRegisterForgetPasswordModule.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);

    void inject(LoginPasswordActivity loginPasswordActivity);

    void inject(RegisterActivity registerActivity);

    void inject(RegisterCodeActivity registerCodeActivity);

    void inject(RegisterPasswordActivity registerPasswordActivity);

    void inject(RegisterPasswordFacebookActivity registerPasswordFacebookActivity);

    void inject(FindPasswordActivity findPasswordActivity);

    void inject(FindPasswordCodeActivity findPasswordCodeActivity);

    void inject(FindPasswordRestActivity findPasswordRestActivity);
}

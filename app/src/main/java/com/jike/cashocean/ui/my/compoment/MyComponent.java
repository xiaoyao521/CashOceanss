package com.jike.cashocean.ui.my.compoment;

import com.jike.cashocean.ui.my.MyAppListActivity;
import com.jike.cashocean.ui.my.MyFragment;
import com.jike.cashocean.ui.my.module.MyModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MyModule.class})
public interface MyComponent {
    void inject(MyFragment myFragment);

    void inject(MyAppListActivity myAppListActivity);
}

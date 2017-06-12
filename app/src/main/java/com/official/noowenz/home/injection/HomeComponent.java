package com.official.noowenz.home.injection;


import com.official.noowenz.home.view.HomeActivity;
import com.official.noowenz.injection.ActivityScope;
import com.official.noowenz.injection.component.NetComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = NetComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}

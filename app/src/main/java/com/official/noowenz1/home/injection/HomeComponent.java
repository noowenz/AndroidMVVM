package com.official.noowenz1.home.injection;


import com.official.noowenz1.home.view.HomeActivity;
import com.official.noowenz1.injection.ActivityScope;
import com.official.noowenz1.injection.component.NetComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = NetComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}

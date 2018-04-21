package com.sugarbeats.service;

import com.badlogic.gdx.Gdx;
import com.sugarbeats.game.entity.EntityComponent;

public abstract class ServiceLocator {
    public static EntityComponent entityComponent;
    public static AppComponent appComponent;

 //   public static void initializeAppComponent(IPlayService networkService) {
   //     appComponent = DaggerAppComponent.builder().appModule(new AppModule(networkService)).build();
    //}

    public static AppComponent getAppComponent() {
        Gdx.app.debug("ServiceLocator", "return appComponent;");
        return appComponent;

    }

    public static void initializeAppComponent(IPlayService networkService) {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(networkService)).build();
    }
}

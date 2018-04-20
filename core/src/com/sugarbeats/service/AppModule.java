package com.sugarbeats.service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private IPlayService playService;

    public AppModule(IPlayService playService) {
        this.playService = playService;
    }

    @Provides
    @Singleton
    IPlayService networkService() {
        return playService;
    }
}

//    @Provides
//    @Singleton
//    no.ntnu.tdt4240.asteroids.service.ISettingsService provideSettingsService() {
//        return settingsService;
//    }

//    @Provides
//    @Singleton
//    AssetService provideAssetService() {
//        return new AssetService();
//    }

//    @Provides
//    @Singleton
//    AudioService provideAudioManager(AssetService assetService, no.ntnu.tdt4240.asteroids.service.ISettingsService settingsService) {
//        return new AudioService(assetService, settingsService);
//    }

//    @Provides
//    @Singleton
//    AnimationFactory provideAnimationFactory(AssetService assetService) {
//        return new AnimationFactory(assetService);
//    }

//}


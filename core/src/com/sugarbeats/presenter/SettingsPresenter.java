package com.sugarbeats.presenter;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.sugarbeats.SugarBeats;

/**
 * Created by taphan on 11.04.2018.
 */

public class SettingsPresenter extends ScreenAdapter {

    SugarBeats game;
    public SettingsPresenter(SugarBeats game, Screen parent) {
        this.game = game;
    }
}

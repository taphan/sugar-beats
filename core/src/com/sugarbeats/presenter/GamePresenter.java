package com.sugarbeats.presenter;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.sugarbeats.SugarBeats;

/**
 * Created by taphan on 11.04.2018.
 */

public class GamePresenter extends ScreenAdapter{

    SugarBeats game;
    private Screen screen;
    protected final PooledEngine engine;


    public GamePresenter(SugarBeats game, Screen parent) {
        this.game = game;
        this.screen = screen;
        engine = new PooledEngine();

    }
}

package com.sugarbeats.view;

import com.badlogic.gdx.graphics.Texture;
import com.sugarbeats.SugarBeats;

/**
 * Created by taphan on 08.03.2018.
 */

public class GameView extends BaseView {
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_OVER = 2;
    Texture playBtn;


    public GameView(SugarBeats game) {
        super(game.getBatch());


    }

    @Override
    public void show() {

    }

}

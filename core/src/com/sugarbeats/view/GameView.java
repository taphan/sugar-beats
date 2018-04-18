package com.sugarbeats.view;

import com.badlogic.gdx.graphics.Texture;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.service.AssetService;

/**
 * Created by taphan on 08.03.2018.
 */

public class GameView extends BaseView {
    SugarBeats game;
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_OVER = 2;
    Texture playBtn;


    public GameView(SugarBeats game) {
        super(game.getBatch());
        this.game = game;

    }

    @Override
    public void update (float delta) {

    }

    @Override
    public void draw() {
        game.batch.begin();
        game.batch.draw(AssetService.fireBtn, SugarBeats.WIDTH - AssetService.fireBtn.getWidth()/5, AssetService.fireBtn.getWidth()/30, AssetService.fireBtn.getWidth() / 6, AssetService.fireBtn.getHeight() / 6);
        game.batch.end();
    }

    @Override
    public void show() {

    }

}

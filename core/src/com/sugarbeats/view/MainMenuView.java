package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.game.SugarBeats;

import java.awt.Rectangle;

/**
 * Created by taphan on 11.04.2018.
 */

public class MainMenuView extends BaseView{
    SugarBeats game;
    OrthographicCamera guiCam;
    Rectangle playBounds;
    Rectangle helpBounds;
    Rectangle settingBounds;
    Vector3 touchPoint;

    public MainMenuView(SugarBeats game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
        settingBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
        helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
        touchPoint = new Vector3();
    }

    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                game.setScreen(new GameView(game));
                return;
            }
            if (settingBounds.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                game.setScreen(new SettingsView(game));
                return;
            }
            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                game.setScreen(new HelpView(game));
                return;
            }

        }
    }

    public void draw() {
        Texture img = new Texture("badlogic.jpg");
        game.batch.begin();
        game.batch.draw(img, 0, 0);
        game.batch.end();
    }

    public void render (float delta) {
        update();
        draw();
    }


}

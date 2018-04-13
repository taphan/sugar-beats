package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.presenter.MainMenuPresenter;

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
    Texture playBtn;
    Texture settingBtn;
    Texture helpBtn;
    Vector3 touchPoint;
    MainMenuPresenter.ViewController controller;

    public MainMenuView(SugarBeats game, MainMenuPresenter.ViewController controller) {
        super(game.batch);
        this.game = game;
        this.controller = controller;

        //guiCam = new OrthographicCamera(320, 480);
        //guiCam.position.set(320 / 2, 480 / 2, 0);
        playBtn = new Texture("button_play.png");
        settingBtn = new Texture("button_settings.png");
        helpBtn = new Texture("button_help.png");
        playBounds = new Rectangle(SugarBeats.WIDTH / 2 - playBtn.getWidth() / 2, SugarBeats.HEIGHT / 2, playBtn.getWidth(), playBtn.getHeight());
        settingBounds = new Rectangle(SugarBeats.WIDTH / 2 + settingBtn.getWidth() / 2, SugarBeats.HEIGHT / 4, settingBtn.getWidth(), settingBtn.getHeight());
        helpBounds = new Rectangle(SugarBeats.WIDTH / 2 - helpBtn.getWidth() * 3 / 2, SugarBeats.HEIGHT / 4, helpBtn.getWidth(), helpBtn.getHeight());
        System.out.println(helpBounds.x + ", " + helpBounds.y);
        touchPoint = new Vector3();
    }

    @Override
    public void update (float delta) {
        if (Gdx.input.justTouched()) {
            // The rectangles are inverted on the screen compared to the actual buttons...
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println(touchPoint.x + ", " + touchPoint.y);
            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("playBounds touched");
                controller.onPlay();
                return;
            }
            if (settingBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("settingBounds" + touchPoint.x + touchPoint.y);
                controller.onSettings();
                return;
            }
            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("helpBounds"+ helpBounds.x+ helpBounds.y);
                controller.onHelp();
            }

        }
    }

    @Override
    public void show() {

    }

    @Override
    public void draw() {
        game.batch.begin();
        game.batch.draw(playBtn, SugarBeats.WIDTH / 2 - playBtn.getWidth() / 2, SugarBeats.HEIGHT / 2);
        game.batch.draw(settingBtn, SugarBeats.WIDTH / 2 + settingBtn.getWidth() / 2, SugarBeats.HEIGHT / 4);
        game.batch.draw(helpBtn, SugarBeats.WIDTH / 2 - helpBtn.getWidth() * 3 / 2, SugarBeats.HEIGHT / 4);
        // There is problem with the y coordinate used by draw method
        // This one has origo on the bottom left corner, while the other one used for Rectangle
        // and the rest of touch points are on the upper left corner
        game.batch.end();
    }

}

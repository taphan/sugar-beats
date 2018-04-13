package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.presenter.MainMenuPresenter;


/**
 * Created by taphan on 11.04.2018.
 */

public class MainMenuView extends BaseView{
    SugarBeats game;
    Rectangle playBounds;
    Rectangle helpBounds;
    Rectangle settingBounds;

    Texture playBtn;
    Texture settingBtn;
    Texture helpBtn;
    Vector3 touchPoint;
    MainMenuPresenter.ViewController controller;

    private static final int WIDTH = SugarBeats.VIRTUAL_WIDTH;
    private static final int HEIGHT = SugarBeats.VIRTUAL_HEIGHT;
    Integer height;

    public MainMenuView(SugarBeats game, MainMenuPresenter.ViewController controller) {
        super(game.batch);
        this.game = game;
        this.controller = controller;

        playBtn = new Texture("button_play.png");
        settingBtn = new Texture("button_settings.png");
        helpBtn = new Texture("button_help.png");
        playBounds = new Rectangle(WIDTH / 2 - playBtn.getWidth() / 2, HEIGHT - playBtn.getHeight() - HEIGHT / 2, playBtn.getWidth(), playBtn.getHeight());
        settingBounds = new Rectangle(WIDTH / 2 + settingBtn.getWidth() / 2, HEIGHT - settingBtn.getHeight() - HEIGHT / 4, settingBtn.getWidth(), settingBtn.getHeight());
        helpBounds = new Rectangle(WIDTH / 2 - helpBtn.getWidth() * 3 / 2, HEIGHT - helpBtn.getHeight() - HEIGHT / 4, helpBtn.getWidth(), helpBtn.getHeight());
        touchPoint = new Vector3();
        System.out.println(playBtn.getWidth() + "" + playBtn.getHeight());
    }

    @Override
    public void update (float delta) {
        if (Gdx.input.justTouched()) {

            // Set touch point to check for whether a menu button has been pressed
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onPlay();
                return;
            }
            if (settingBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onSettings();
                return;
            }
            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onHelp();
            }

        }
    }

    @Override
    public void show() {

    }

    @Override
    public void draw() {
        // Draw menu buttons
        // TODO: Add title picture/text and background
        // TODO: Discuss about whether its necessary to constantly call the draw() function from render, is it enough to call it once during creation of class?
        game.batch.begin();
        game.batch.draw(playBtn, WIDTH / 2 - playBtn.getWidth() / 2, HEIGHT / 2);
        game.batch.draw(settingBtn, WIDTH / 2 + settingBtn.getWidth() / 2, HEIGHT / 4);
        game.batch.draw(helpBtn, WIDTH / 2 - helpBtn.getWidth() * 3 / 2, HEIGHT / 4);
        game.batch.end();
    }
}

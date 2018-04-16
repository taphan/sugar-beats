package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    Rectangle hsBounds;

    Texture playBtn;
    Texture settingBtn;
    Texture helpBtn;
    Texture hsBtn;
    Vector3 touchPoint;
    MainMenuPresenter.ViewController controller;
    OrthographicCamera cam;

    private static final int WIDTH = SugarBeats.WIDTH;
    private static final int HEIGHT = SugarBeats.HEIGHT;
    Integer height;

    public MainMenuView(SugarBeats game, MainMenuPresenter.ViewController controller) {
        super(game.batch);
        this.game = game;
        this.controller = controller;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH, HEIGHT);

        playBtn = new Texture("button_play.png");
        settingBtn = new Texture("button_settings.png");
        helpBtn = new Texture("button_help.png");
        hsBtn = new Texture("button_highscore.png");
        playBounds = new Rectangle(WIDTH / 2 - playBtn.getWidth()/3 / 2, HEIGHT / 2, playBtn.getWidth() / 3, playBtn.getHeight() / 3);
        settingBounds = new Rectangle(WIDTH / 2 + settingBtn.getWidth()/3 / 2 + 20, HEIGHT / 4, settingBtn.getWidth() / 3, settingBtn.getHeight() / 3);
        helpBounds = new Rectangle(WIDTH / 2 - helpBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 4, helpBtn.getWidth() / 3, helpBtn.getHeight() / 3);
        hsBounds = new Rectangle(WIDTH / 2 - playBtn.getWidth()/3 / 2, HEIGHT / 4, helpBtn.getWidth() / 3, helpBtn.getHeight() / 3);
        touchPoint = new Vector3();
    }

    @Override
    public void update (float delta) {
        if (Gdx.input.justTouched()) {

            // Set touch point to check for whether a menu button has been pressed
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
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
            if (hsBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("hs bounds");
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
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(playBtn, WIDTH / 2 - playBtn.getWidth()/3 / 2, HEIGHT / 2, playBtn.getWidth() / 3, playBtn.getHeight() / 3);
        game.batch.draw(settingBtn, WIDTH / 2 + settingBtn.getWidth()/3 / 2 + 20, HEIGHT / 4, settingBtn.getWidth() / 3, settingBtn.getHeight() / 3);
        game.batch.draw(helpBtn, WIDTH / 2 - helpBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 4, helpBtn.getWidth() / 3, helpBtn.getHeight() / 3);
        game.batch.draw(hsBtn, WIDTH / 2 - playBtn.getWidth()/3 / 2, HEIGHT / 4, playBtn.getWidth() / 3, playBtn.getHeight() / 3);
        game.batch.end();
    }
}

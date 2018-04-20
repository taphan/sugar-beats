package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.presenter.MainMenuPresenter;
import com.sugarbeats.service.AssetService;
import com.sugarbeats.service.AudioService;

/**
 * Created by taphan on 11.04.2018.
 */

public class MainMenuView extends BaseView{
    SugarBeats game;

    Rectangle playBounds; //TODO: Move these to assetservice?
    Rectangle helpBounds;
    Rectangle settingBounds;
    Rectangle hsBounds;
    Rectangle highScoreBounds;
    Rectangle achieveMentsBounds;
    Rectangle multiplayerBounds;

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

        playBounds = new Rectangle(WIDTH / 2 + AssetService.playBtn.getWidth()/3 / 2 + 120, HEIGHT / 2 + 25, AssetService.playBtn.getWidth() / 3, AssetService.playBtn.getHeight() / 3);
        settingBounds = new Rectangle(WIDTH / 2 - AssetService.settingBtn.getWidth()/3*3 / 2 - 135, HEIGHT / 2 - 30, AssetService.settingBtn.getWidth() / 3, AssetService.settingBtn.getHeight() / 3);
        helpBounds = new Rectangle(WIDTH / 2 - AssetService.helpBtn.getWidth()/3 * 3 / 2 - 130, HEIGHT / 4 + 18, AssetService.helpBtn.getWidth() / 3, AssetService.helpBtn.getHeight() / 3);
        hsBounds = new Rectangle(WIDTH / 2 + AssetService.hsBtn.getWidth()/3*3 / 2 - 15, HEIGHT / 4 + 20, AssetService.hsBtn.getWidth() / 3, AssetService.hsBtn.getHeight() / 3);
        achieveMentsBounds = new Rectangle(WIDTH / 2 - AssetService.acBtn.getWidth()/3 *3/ 2 - 135, HEIGHT / 2 + 5, AssetService.acBtn.getWidth() / 3, AssetService.acBtn.getHeight() / 3);
        multiplayerBounds= new Rectangle(WIDTH / 2 + AssetService.mBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 2 - 25, AssetService.mBtn.getWidth() / 3, AssetService.mBtn.getHeight() / 3);

        touchPoint = new Vector3();
    }

    @Override
    public void update (float delta) {
        if (Gdx.input.justTouched()) {

            // Set touch point to check for whether a menu button has been pressed
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                controller.onPlay();
                return;
            }
            if (settingBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                controller.onSettings();
                return;
            }
            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                controller.onHelp();
            }
            if (hsBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                controller.onShowLeaderboard();
                System.out.println("HIGHSCORE bounds");
            }
            if (achieveMentsBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onShowAchievments();
                System.out.println("Achievements bounds");
            }
            if (multiplayerBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onMultiPlayer();
                System.out.println("Multiplayer bounds");
            }

        }
    }

    @Override
    public void show() {

    }

    @Override
    public void draw() {
        // Draw menu buttons
        // TODO: Add title picture/text and background (K: Jeg har laget en quickfix på dette, men ikke en endelig losning)
        // TODO: Discuss about whether its necessary to constantly call the draw() function from render, is it enough to call it once during creation of class?
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();
        game.batch.draw(AssetService.mainMenu, 0, 0, WIDTH, HEIGHT); //BAD!! Needs to be more dynamic
        game.batch.draw(AssetService.playBtn, WIDTH / 2 + AssetService.playBtn.getWidth()/3 / 2 + 120, HEIGHT / 2 + 25, AssetService.playBtn.getWidth() / 3, AssetService.playBtn.getHeight() / 3);
        game.batch.draw(AssetService.settingBtn, WIDTH / 2 - AssetService.settingBtn.getWidth()/3*3 / 2 - 135, HEIGHT / 2 - 30, AssetService.settingBtn.getWidth() / 3, AssetService.settingBtn.getHeight() / 3);
        game.batch.draw(AssetService.helpBtn, WIDTH / 2 - AssetService.helpBtn.getWidth()/3 * 3 / 2 - 130, HEIGHT / 4 + 18, AssetService.helpBtn.getWidth() / 3, AssetService.helpBtn.getHeight() / 3);
        game.batch.draw(AssetService.hsBtn, WIDTH / 2 + AssetService.hsBtn.getWidth()/3*3 / 2 - 15, HEIGHT / 4 + 20, AssetService.hsBtn.getWidth() / 3 + 10, AssetService.hsBtn.getHeight() / 3);
        game.batch.draw(AssetService.acBtn, WIDTH / 2 - AssetService.acBtn.getWidth()/3 *3/ 2 - 135, HEIGHT / 2 + 5, AssetService.acBtn.getWidth() / 3, AssetService.acBtn.getHeight() / 3);
        game.batch.draw(AssetService.mBtn, WIDTH / 2 + AssetService.mBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 2 - 25, AssetService.mBtn.getWidth() / 3, AssetService.mBtn.getHeight() / 3);
        game.batch.end();
    }
}
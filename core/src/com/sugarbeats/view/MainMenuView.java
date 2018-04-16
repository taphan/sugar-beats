package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

    Rectangle playBounds;
    Rectangle helpBounds;
    Rectangle settingBounds;
    Rectangle hsBounds;
    Rectangle highScoreBounds;
    Rectangle achieveMentsBounds;
    Rectangle multiplayerBounds;

    Texture playBtn;
    Texture settingBtn;
    Texture helpBtn;
    Texture hsBtn;
    Texture acBtn;
    Texture mBtn;



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
        acBtn = new Texture("button_achievements.png");
        mBtn = new Texture("button_multiplayer.png");

        playBounds = new Rectangle(WIDTH / 2 - playBtn.getWidth()/3 / 2, HEIGHT / 2, playBtn.getWidth() / 3, playBtn.getHeight() / 3);
        settingBounds = new Rectangle(WIDTH / 2 + settingBtn.getWidth()/3 / 2 + 20, HEIGHT / 4, settingBtn.getWidth() / 3, settingBtn.getHeight() / 3);
        helpBounds = new Rectangle(WIDTH / 2 - helpBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 4, helpBtn.getWidth() / 3, helpBtn.getHeight() / 3);
        hsBounds = new Rectangle(WIDTH / 2 - playBtn.getWidth()/3 / 2, HEIGHT / 4, helpBtn.getWidth() / 3, helpBtn.getHeight() / 3);
        highScoreBounds = new Rectangle(WIDTH / 2 - hsBtn.getWidth()/3 / 2, HEIGHT / 4, hsBtn.getWidth() / 3, hsBtn.getHeight() / 3);
        achieveMentsBounds = new Rectangle(WIDTH / 2 + settingBtn.getWidth()/3 / 2 + 20, HEIGHT / 2, playBtn.getWidth() / 3, playBtn.getHeight() / 3);
        multiplayerBounds= new Rectangle(WIDTH / 2 - helpBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 2, mBtn.getWidth() / 3, mBtn.getHeight() / 3);





        touchPoint = new Vector3();
    }

    @Override
    public void update (float delta) {
        if (Gdx.input.justTouched()) {

            // Set touch point to check for whether a menu button has been pressed
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (AssetService.playBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                controller.onPlay();
                return;
            }
            if (AssetService.settingBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                controller.onSettings();
                return;
            }
            if (AssetService.helpBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                controller.onHelp();
            }
            if (AssetService.hsBounds.contains(touchPoint.x, touchPoint.y)) {
                AudioService.playSound(AudioService.buttonPressSound);
                System.out.println("hs bounds");
            }
            if (hsBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onShowLeaderboard();
                System.out.println("HIGHSCORE bounds");
            }
            if (achieveMentsBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onShowAchievments();
                System.out.println("Achievements bounds");
            }
            if (multiplayerBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onMultiPlayer();
                System.out.println("Achievements bounds");
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
        game.batch.draw(AssetService.playBtn, WIDTH / 2 - AssetService.playBtn.getWidth()/3 / 2, HEIGHT / 2, AssetService.playBtn.getWidth() / 3, AssetService.playBtn.getHeight() / 3);
        game.batch.draw(AssetService.settingBtn, WIDTH / 2 + AssetService.settingBtn.getWidth()/3 / 2 + 20, HEIGHT / 4, AssetService.settingBtn.getWidth() / 3, AssetService.settingBtn.getHeight() / 3);
        game.batch.draw(AssetService.helpBtn, WIDTH / 2 - AssetService.helpBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 4, AssetService.helpBtn.getWidth() / 3, AssetService.helpBtn.getHeight() / 3);
        game.batch.draw(AssetService.hsBtn, WIDTH / 2 - AssetService.playBtn.getWidth()/3 / 2, HEIGHT / 4, AssetService.playBtn.getWidth() / 3, AssetService.playBtn.getHeight() / 3);
        game.batch.draw(acBtn, WIDTH / 2 + settingBtn.getWidth()/3 / 2 + 20, HEIGHT / 2, playBtn.getWidth() / 3, playBtn.getHeight() / 3);
        game.batch.draw(mBtn, WIDTH / 2 - helpBtn.getWidth()/3 * 3 / 2 - 20, HEIGHT / 2, mBtn.getWidth() / 3, mBtn.getHeight() / 3);

        game.batch.end();
    }
}
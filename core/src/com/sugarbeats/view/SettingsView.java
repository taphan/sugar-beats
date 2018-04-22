package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.presenter.HelpPresenter;
import com.sugarbeats.presenter.SettingsPresenter;
import com.sugarbeats.service.AssetService;
import com.sugarbeats.service.AudioService;


/**
 * Created by taphan on 08.03.2018.
 */

public class SettingsView extends BaseView{


    SugarBeats game;
    SettingsPresenter.ViewController controller;
    Vector3 touchPoint;
    OrthographicCamera cam;

    private static final int WIDTH = SugarBeats.WIDTH;
    private static final int HEIGHT = SugarBeats.HEIGHT;
    private static boolean mute = false;

    Texture backBtn;
    Texture unmuteBtn;
    Texture muteBtn;
    Texture musicBtn;

    Rectangle unmuteBounds;
    Rectangle muteBounds;
    Rectangle backBounds;

    public SettingsView(SugarBeats game, SettingsPresenter.ViewController viewController) {
        super(game.getBatch());
        this.game = game;
        this.controller = viewController;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH, HEIGHT);

        unmuteBtn = AssetService.unmute_btn;
        muteBtn = AssetService.mute_btn;
        musicBtn = unmuteBtn;
        backBtn = new Texture("button_back.png");

        unmuteBounds = new Rectangle(WIDTH/2 - unmuteBtn.getWidth()/15/2, HEIGHT/2 - 40, unmuteBtn.getWidth()/15,unmuteBtn.getHeight()/15);
        muteBounds = new Rectangle(WIDTH/2 - muteBtn.getWidth()/15/2, HEIGHT/2 - 40, muteBtn.getWidth()/15,muteBtn.getHeight()/15);
        backBounds = new Rectangle(WIDTH / 2 - backBtn.getWidth()/3 / 2, HEIGHT - 330, backBtn.getWidth() / 3, backBtn.getHeight() / 3 );

        touchPoint = new Vector3();
    }
    @Override
    public void update (float delta) {
        if (Gdx.input.justTouched()) {
            // Set touch point to check for whether a menu button has been pressed
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (backBounds.contains(touchPoint.x, touchPoint.y)) {
                controller.onBack();
            }

            if (unmuteBounds.contains(touchPoint.x, touchPoint.y)) {
                if(!mute){
                    musicBtn = muteBtn;
                    setMute();
                    AudioService.menuMusic.stop();

                }else if(mute){
                    musicBtn = unmuteBtn;
                    setUnMute();
                    AudioService.menuMusic.play();
                    AudioService.menuMusic.isLooping();
                }
            }
        }
    }



    public static void setMute(){
        mute = true;

    }

    public static void setUnMute(){
        mute = false;

    }
    @Override
    public void show() {

    }

    @Override
    public void draw() {
        // Draw menu buttons
        // TODO: Add title picture/text and background
        // TODO: Discuss about whether its necessary to constantly call the draw() function from render, is it enough to call it once during creation of class?
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(AssetService.settingsMenu, 0, 0, WIDTH, HEIGHT); //BAD!! Needs to be more dynamic
        game.batch.draw(AssetService.title_Setting,WIDTH/2 - AssetService.title_Setting.getWidth()/3/2,HEIGHT/2 + 70,AssetService.title_Setting.getWidth()/3,AssetService.title_Setting.getHeight()/3);
        game.batch.draw(musicBtn,WIDTH/2 - musicBtn.getWidth()/15/2, HEIGHT/2 - 40, musicBtn.getWidth()/15,musicBtn.getHeight()/15);
        game.batch.draw(backBtn,WIDTH / 2 - backBtn.getWidth()/3 / 2, HEIGHT - 330, backBtn.getWidth() / 3, backBtn.getHeight() / 3 );
        game.batch.end();


    }
}


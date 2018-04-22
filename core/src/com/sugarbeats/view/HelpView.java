package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.presenter.HelpPresenter;
import com.sugarbeats.presenter.MultiPlayerMenuPresenter;
import com.sugarbeats.service.AssetService;

/**
 * Created by taphan on 11.04.2018.
 */

public class HelpView extends BaseView{

    SugarBeats game;
    HelpPresenter.ViewController controller;
    Vector3 touchPoint;
    OrthographicCamera cam;

    Texture backBtn;

    Rectangle backBounds;



    private static final int WIDTH = SugarBeats.WIDTH;
    private static final int HEIGHT = SugarBeats.HEIGHT;

    public HelpView(SugarBeats game, HelpPresenter.ViewController viewController) {
        super(game.getBatch());
        this.game = game;
        this.controller = viewController;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH, HEIGHT);

        backBtn = new Texture("button_back.png");

        backBounds = new Rectangle(WIDTH / 2 - backBtn.getWidth()/3 / 2, HEIGHT - 350, backBtn.getWidth() / 3, backBtn.getHeight() / 3);

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
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(AssetService.settingsMenu, 0, 0, WIDTH, HEIGHT); //BAD!! Needs to be more dynamic
        game.batch.draw(AssetService.title_Help,WIDTH/2 - AssetService.title_Help.getWidth()/3/2,HEIGHT/2 + 70,AssetService.title_Help.getWidth()/3,AssetService.title_Help.getHeight()/3);
        game.batch.draw(backBtn,WIDTH / 2 - backBtn.getWidth()/3 / 2, HEIGHT - 330, backBtn.getWidth() / 3, backBtn.getHeight() / 3 );
        game.batch.end();


    }
}

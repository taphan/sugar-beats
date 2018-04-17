package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.presenter.MultiPlayerMenuPresenter;

public class MultiPlayerMenuView extends BaseView {
    SugarBeats game;
    MultiPlayerMenuPresenter.ViewController controller;
    Vector3 touchPoint;
    OrthographicCamera cam;

    private static final int WIDTH = SugarBeats.WIDTH;
    private static final int HEIGHT = SugarBeats.HEIGHT;

    Texture inviteBtn;
    Texture crLBtn;
    Texture qSBtn;
    Texture backBtn;

    Rectangle inviteBounds;
    Rectangle crBounds;
    Rectangle qSBounds;
    Rectangle backBounds;


    public MultiPlayerMenuView(SugarBeats game, MultiPlayerMenuPresenter.ViewController viewController) {
        super(game.getBatch());
        this.game = game;
        this.controller = viewController;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH, HEIGHT);

        qSBtn = new Texture("button_quick-start.png");
        inviteBtn = new Texture("button_invite.png");
        backBtn = new Texture("button_back.png");
        crLBtn = new Texture("button_create-lobby.png");

        qSBounds = new Rectangle(WIDTH / 2 - qSBtn.getWidth()/3 / 2, HEIGHT - 70, qSBtn.getWidth() / 3, qSBtn.getHeight() / 3);
        inviteBounds = new Rectangle(WIDTH / 2 - inviteBtn.getWidth()/3 / 2, HEIGHT - 260, inviteBtn.getWidth() / 3, inviteBtn.getHeight() / 3);
        crBounds = new Rectangle(WIDTH / 2 - crLBtn.getWidth()/3 / 2, HEIGHT - 165, crLBtn.getWidth() / 3, crLBtn.getHeight() / 3);
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
            if (inviteBounds.contains(touchPoint.x, touchPoint.y)){
                controller.onInvite();
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
        game.batch.draw(qSBtn, WIDTH / 2 - qSBtn.getWidth()/3 / 2, HEIGHT - 70, qSBtn.getWidth() / 3, qSBtn.getHeight() / 3);
        game.batch.draw(inviteBtn,WIDTH / 2 - inviteBtn.getWidth()/3 / 2, HEIGHT - 260, inviteBtn.getWidth() / 3, inviteBtn.getHeight() / 3);
        game.batch.draw(crLBtn, WIDTH / 2 - crLBtn.getWidth()/3 / 2, HEIGHT - 165, crLBtn.getWidth() / 3, crLBtn.getHeight() / 3);
        game.batch.draw(backBtn,WIDTH / 2 - backBtn.getWidth()/3 / 2, HEIGHT - 350, backBtn.getWidth() / 3, backBtn.getHeight() / 3 );
        game.batch.end();


    }


}

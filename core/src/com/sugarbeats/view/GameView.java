package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.presenter.GamePresenter;
import com.sugarbeats.service.AssetService;

/**
 * Created by taphan on 08.03.2018.
 */

public class GameView extends BaseView {
    SugarBeats game;
    GamePresenter presenter;
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_OVER = 2;

    private final OrthographicCamera cam;

    Vector3 touchPoint;
    Rectangle fireBound;
    Rectangle leftBound;
    Rectangle rightBound;


    public GameView(SugarBeats game, GamePresenter presenter) {
        super(game.getBatch());
        this.game = game;
        this.presenter = presenter;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, SugarBeats.WIDTH, SugarBeats.HEIGHT);

        fireBound = new Rectangle(SugarBeats.WIDTH - AssetService.fireBtn.getWidth()/5, AssetService.fireBtn.getWidth()/30, AssetService.fireBtn.getWidth() / 6, AssetService.fireBtn.getHeight() / 6);
        leftBound = new Rectangle(AssetService.leftBtn.getWidth()/5, AssetService.leftBtn.getWidth()/30, AssetService.leftBtn.getWidth() / 6, AssetService.leftBtn.getHeight() / 6);
        rightBound = new Rectangle(AssetService.rightBtn.getWidth()/2, AssetService.leftBtn.getWidth()/30, AssetService.leftBtn.getWidth() / 6, AssetService.leftBtn.getHeight() / 6);
        System.out.println(AssetService.leftBtn.getWidth()/5 + " " + AssetService.leftBtn.getWidth()/30+ " " +
                AssetService.leftBtn.getWidth() / 6 + " " + AssetService.leftBtn.getHeight() / 6);
        touchPoint = new Vector3();
    }

    @Override
    public void update (float delta) {
        for(int i = 0; i < 5; i++) {
            if(touches.get(i).touched) {
                cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
                System.out.println(touchPoint.x + " ," + touchPoint.y);
                if (leftBound.contains(touchPoint.x, touchPoint.y)) {
                    presenter.updateKeyPress(0);
                }
                if (rightBound.contains(touchPoint.x, touchPoint.y)) {
                    presenter.updateKeyPress(1);
                }
            }
        }
    }

    @Override
    public void draw() {
        game.batch.begin();
        game.batch.draw(AssetService.fireBtn, SugarBeats.WIDTH - AssetService.fireBtn.getWidth()/5, AssetService.fireBtn.getWidth()/30, AssetService.fireBtn.getWidth() / 6, AssetService.fireBtn.getHeight() / 6);
        game.batch.draw(AssetService.leftBtn, AssetService.leftBtn.getWidth()/5, AssetService.leftBtn.getWidth()/30, AssetService.leftBtn.getWidth() / 6, AssetService.leftBtn.getHeight() / 6);
        game.batch.draw(AssetService.rightBtn, AssetService.rightBtn.getWidth()/2, AssetService.leftBtn.getWidth()/30, AssetService.leftBtn.getWidth() / 6, AssetService.leftBtn.getHeight() / 6);
        game.batch.end();
    }

    @Override
    public void show() {

    }
}

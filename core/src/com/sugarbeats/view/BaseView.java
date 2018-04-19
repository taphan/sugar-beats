package com.sugarbeats.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sugarbeats.SugarBeats;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taphan on 08.03.2018.
 */

public abstract class BaseView extends Stage implements IView, InputProcessor {

    class TouchInfo {
        public float touchX = 0;
        public float touchY = 0;
        public boolean touched = false;
    }
    private final OrthographicCamera cam;
    protected Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();

    BaseView(Batch batch) {
        super(new FitViewport(640, 360), batch);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, SugarBeats.WIDTH, SugarBeats.HEIGHT);

        Gdx.input.setInputProcessor(this);
        for(int i = 0; i < 5; i++){
            touches.put(i, new TouchInfo());
        }
    }

    @Override
    public void update(float dt) {
        act(dt);
    }

    @Override
    public final InputProcessor getInputProcessor() {
        return this;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer < 5){
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
            touches.get(pointer).touched = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer < 5){
            touches.get(pointer).touchX = 0;
            touches.get(pointer).touchY = 0;
            touches.get(pointer).touched = false;
        }
        return true;
    }

}

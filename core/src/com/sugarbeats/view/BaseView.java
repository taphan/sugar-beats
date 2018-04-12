package com.sugarbeats.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by taphan on 08.03.2018.
 */

public abstract class BaseView extends Stage implements IView {

    BaseView(Batch batch) {
        super(new FitViewport(640, 360), batch);
    }

    @Override
    public void update(float dt) {
        act(dt);
    }




}

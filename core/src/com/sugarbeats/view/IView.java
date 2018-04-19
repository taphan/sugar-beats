package com.sugarbeats.view;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by taphan on 11.04.2018.
 */

public interface IView {

    void update(float delta);

    void show();

    void draw();

    InputProcessor getInputProcessor();

}

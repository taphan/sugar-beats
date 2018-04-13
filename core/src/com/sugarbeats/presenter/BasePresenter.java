package com.sugarbeats.presenter;

import com.badlogic.gdx.ScreenAdapter;

import com.sugarbeats.view.IView;

/**
 * Created by taphan on 11.04.2018.
 */


abstract class BasePresenter extends ScreenAdapter {

    @SuppressWarnings("unused")
    public abstract IView getView();

    @Override
    public void render(float delta) {
        getView().update(delta);
        getView().draw();
    }

    @Override
    public void show() {
        super.show();
        getView().show();
        //Gdx.input.setInputProcessor(getView().getInputProcessor());
    }
}

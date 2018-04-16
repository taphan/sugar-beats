package com.sugarbeats.presenter;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.view.HelpView;
import com.sugarbeats.view.IView;

/**
 * Created by taphan on 11.04.2018.
 */

public class HelpPresenter extends BasePresenter {

    SugarBeats game;
    IView view;
    Screen parent;

    public HelpPresenter(SugarBeats game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.view = new HelpView(game, new ViewController());
    }

    @Override
    public IView getView() {
        return view;
    }

    public class ViewController {

        public void onBack() {
            game.setScreen(parent);

        }
    }
}

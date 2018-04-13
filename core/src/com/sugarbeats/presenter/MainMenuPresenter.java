package com.sugarbeats.presenter;

import com.badlogic.gdx.Gdx;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.view.IView;
import com.sugarbeats.view.MainMenuView;

/**
 * Created by taphan on 11.04.2018.
 */

public class MainMenuPresenter extends BasePresenter{
    private final SugarBeats game;
    private final IView view;

    public MainMenuPresenter(final SugarBeats game) {
        this.game = game;
        this.view = new MainMenuView(game, new ViewController());
    }

    @Override
    public IView getView() {
        return view;
    }

    public class ViewController {

        public void onPlay() {
            game.setScreen(new GamePresenter(game, MainMenuPresenter.this));
        }

        public void onHelp() {
            game.setScreen(new HelpPresenter(game, MainMenuPresenter.this));
        }

        public void onSettings() {
            game.setScreen(new SettingsPresenter(game, MainMenuPresenter.this));
        }

        public void onQuit() {
            Gdx.app.exit();
        }

    }
}

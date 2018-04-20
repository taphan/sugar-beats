package com.sugarbeats.presenter;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.view.IView;
import com.sugarbeats.view.MainMenuView;
import com.sugarbeats.view.MultiPlayerMenuView;

public class MultiPlayerMenuPresenter  extends BasePresenter {

    SugarBeats game;
    private final IView view;

    public MultiPlayerMenuPresenter(SugarBeats game) {
        this.game = game;
        this.view = new MultiPlayerMenuView(game, new ViewController());

    }

    @Override
    public IView getView() {
        return view;
    }

    public class ViewController {

        public void onBack() {
            game.setScreen(new MainMenuPresenter(game));
        }

        public void onInvite(){
            SugarBeats.playServices.startSelectOpponents(false);
        }


        }

    }


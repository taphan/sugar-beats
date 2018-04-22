package com.sugarbeats;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sugarbeats.presenter.GamePresenter;
import com.sugarbeats.presenter.MainMenuPresenter;
import com.sugarbeats.service.AssetService;
import com.sugarbeats.service.AudioService;
import com.sugarbeats.service.IPlayService;
import com.sugarbeats.service.ServiceLocator;


public class SugarBeats extends Game implements IPlayService.IGameListener {

	public static final int VIRTUAL_WIDTH = 960;
	public static final int VIRTUAL_HEIGHT = 540;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
  	public static final String TAG = "Sugar Beats";

	public SpriteBatch batch;

	public static IPlayService playServices;

	public SugarBeats(IPlayService playServices){
		this.playServices = playServices;
	}

	// For Desktop to work
	public SugarBeats() {}

	@Override
	public void create () {
	    if(Gdx.app.getType() == Application.ApplicationType.Android) {
            playServices.setGameListener(this);
            ServiceLocator.initializeAppComponent(playServices);
            ServiceLocator.getAppComponent().getNetworkService().setGameListener(this);
        }

		Gdx.app.setLogLevel(Application.LOG_DEBUG);


		batch = new SpriteBatch();

		//Load graphics and animations
		AssetService.load();
		//Load music and soundeffects
		AudioService.load();
		// Start the game by presenting the main menu screen
		setScreen(new MainMenuPresenter(this));
	}

	@Override
	public void render () {
		// White default background
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void onMultiplayerGameStarting() {



		Gdx.app.debug(TAG, "onMultiplayerGameStarting:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

		setScreen(new GamePresenter(this, new MainMenuPresenter(this)));
	}
}

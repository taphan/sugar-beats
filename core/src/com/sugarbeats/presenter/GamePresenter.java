package com.sugarbeats.presenter;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.game.World;
import com.sugarbeats.game.entity.system.AnimationSystem;
import com.sugarbeats.game.entity.system.GravitySystem;
import com.sugarbeats.game.entity.system.MovementSystem;
import com.sugarbeats.game.entity.system.PlayerSystem;
import com.sugarbeats.game.entity.system.RenderSystem;
import com.sugarbeats.view.GameView;

/**
 * Created by taphan on 11.04.2018.
 */

public class GamePresenter extends ScreenAdapter{

    SugarBeats game;
    private Screen parent;
    protected final PooledEngine engine;
    World world;
    GameView view;


    public GamePresenter(SugarBeats game, Screen parent) {
        this.game = game;
        this.parent = parent;
        engine = new PooledEngine();
        world = new World(engine);
        view = new GameView(game);
        setupEngine(engine, game.getBatch());
        world.create();

    }

    private void setupEngine(PooledEngine engine, SpriteBatch batch) {
        RenderSystem renderSystem = new RenderSystem(batch);
        PlayerSystem playerSystem = new PlayerSystem(world);
        engine.addSystem(renderSystem);
        engine.addSystem(playerSystem);
        engine.addSystem(new MovementSystem());
        engine.addSystem(new GravitySystem());
        engine.addSystem(new AnimationSystem());


    }

    @Override
    public final void render(float delta) {
        update(delta);
    }

    private void update(float delta) {
        if (delta > 0.1f) delta = 0.1f;
        world.update(delta);
        engine.update(delta);
        updateInput();
    }

    private void updateInput() {
        Application.ApplicationType appType = Gdx.app.getType();
        float veloX = 0.0f;

        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            veloX = Gdx.input.getAccelerometerX();
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) veloX = 5f;
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) veloX = -5f;
        }

        engine.getSystem(PlayerSystem.class).setVelocity(veloX);
    }

}

package com.sugarbeats.presenter;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.game.World;
import com.sugarbeats.game.entity.system.RenderSystem;
import com.sugarbeats.view.GameView;

/**
 * Created by taphan on 11.04.2018.
 */

public class GamePresenter extends ScreenAdapter{

    SugarBeats game;
    private Screen screen;
    protected final PooledEngine engine;
    World world;
    GameView view;


    public GamePresenter(SugarBeats game, Screen parent) {
        this.game = game;
        this.screen = screen;
        engine = new PooledEngine();
        world = new World(engine);
        view = new GameView(game);
        setupEngine(engine, game.getBatch());

    }

    private void setupEngine(PooledEngine engine, SpriteBatch batch) {
        RenderSystem renderSystem = new RenderSystem(batch);
        engine.addSystem(renderSystem);
    }

    @Override
    public final void render(float delta) {
        update(delta);
    }

    private void update(float delta) {
        if (delta > 0.1f) delta = 0.1f;
        world.update(delta);
        engine.update(delta);
    }


}

package com.sugarbeats.presenter;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.game.World;
import com.sugarbeats.game.entity.component.BoundsComponent;
import com.sugarbeats.game.entity.component.PlayerComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TransformComponent;
import com.sugarbeats.game.entity.system.AnimationSystem;
import com.sugarbeats.game.entity.system.BoundsSystem;
import com.sugarbeats.game.entity.system.CollisionSystem;
import com.sugarbeats.game.entity.system.CollisionSystem.CollisionListener;
import com.sugarbeats.game.entity.system.GravitySystem;
import com.sugarbeats.game.entity.system.MovementSystem;
import com.sugarbeats.game.entity.system.PlayerSystem;
import com.sugarbeats.game.entity.system.ProjectileSystem;
import com.sugarbeats.game.entity.system.RenderSystem;
import com.sugarbeats.model.PlayerData;
import com.sugarbeats.service.AudioService;
import com.sugarbeats.service.IPlayService;
import com.sugarbeats.service.ServiceLocator;
import com.sugarbeats.view.GameView;

import java.util.List;

//import sun.rmi.runtime.Log;


/**
 * Created by taphan on 11.04.2018.
 */


public class GamePresenter extends ScreenAdapter implements IPlayService.INetworkListener {


    protected final PooledEngine engine;
    private SugarBeats game;
    private Screen parent;
    private World world;
    private GameView view;
    private CollisionListener collisionListener;
    private IPlayService playService;


    public GamePresenter(SugarBeats game, Screen parent) {
        Gdx.app.debug("GAMEPRESENTER FRA ANDROIDNETWORK", "oneMultiplayerGameStarting!!!!!!!!!!!!!!");
        //Koden stopper her (Rekker ikke å printe ting som blir påkalt tidligere
        //Må få playservice = Androidnetwork,
        if(Gdx.app.getType() == Application.ApplicationType.Android) {
            playService = ServiceLocator.getAppComponent().getNetworkService();
            playService.setNetworkListener(this);
        }

        this.game = game;
        this.parent = parent;
        engine = new PooledEngine();
        world = new World(engine);
        view = new GameView(game, this);

        collisionListener = new CollisionListener() {
            @Override
            public void powerup() {
                AudioService.playSound((AudioService.buttonPressSound));
                System.out.println("Power up sound");
            }

            @Override

            public void ground() {
            }

            @Override
            public void hit() {
                AudioService.playSound(AudioService.damageSound);
                view.health -= 20;
            }

        };

        setupEngine(engine, game.getBatch());
        world.create();

    }

    private void setupEngine(PooledEngine engine, SpriteBatch batch) {
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new PlayerSystem(world));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new GravitySystem());
        engine.addSystem(new ProjectileSystem());

        engine.addSystem(new CollisionSystem(world, collisionListener));
    }

    @Override
    public final void render(float delta) {
        update(delta);
        view.draw();
        view.show();
    }

    private void update(float delta) {
        if (delta > 0.1f) delta = 0.1f;
        updateInput();
        view.update(delta);
        world.update(delta);
        engine.update(delta);
    }

    private void updateInput() {
        float veloX = 0.0f;
        float veloY = 0.0f;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) veloX = -250f;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) veloX = 100f;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            veloY = 250f;
        }

        engine.getSystem(PlayerSystem.class).setVelocity(veloX);
    }

    public void updateKeyPress(int key) {
        float veloX = 0.0f;

        switch (key) {
            case 0:
                // Left button pressed
                veloX = -250f;
                break;
            case 1:
                // Right button pressed
                veloX = 250f;
                break;
        }
        engine.getSystem(PlayerSystem.class).setVelocity(veloX);
    }

    public void updateFireButton(float v0, float angle) {
        ImmutableArray<Entity> players = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundsComponent.class, TransformComponent.class, StateComponent.class).get());
        // TODO: Find player index to current player
        engine.getSystem(PlayerSystem.class).fireProjectile(players.get(0));
        engine.getSystem(ProjectileSystem.class).initializeVelocity(v0, angle);
    }


    @Override
    public void onReliableMessageReceived(String senderParticipantId, int describeContents, byte[] messageData) {

    }

    @Override
    public void onUnreliableMessageReceived(String senderParticipantId, int describeContents, byte[] messageData) {
    }

        @Override
        public void onRoomReady (List < PlayerData > players) {
            Gdx.app.debug("SUGAR BEATS", "onRoomReady: ");
//        addPlayers(players, true);
//        world.initialize();
        }

}

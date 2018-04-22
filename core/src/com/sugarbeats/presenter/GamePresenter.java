package com.sugarbeats.presenter;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
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
import com.sugarbeats.game.entity.system.NetworkSystem;
import com.sugarbeats.game.entity.system.PlayerSystem;
import com.sugarbeats.game.entity.system.RenderSystem;
import com.sugarbeats.model.PlayerData;
import com.sugarbeats.service.IPlayService;

import com.sugarbeats.service.ServiceLocator;
import com.sugarbeats.view.GameView;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//import sun.rmi.runtime.Log;


import com.sugarbeats.view.GameView;

import java.util.List;


/**
 * Created by taphan on 11.04.2018.
 */


public class GamePresenter extends ScreenAdapter implements IPlayService.INetworkListener {
    private final String TAG = "SUGAR BEATS :GamePresenter-----------------------------------------------------------------------------------";

    SugarBeats game;
    private Screen parent;
    protected final PooledEngine engine;
    World world;
    GameView view;

    HashMap<String, PlayerData> players = new HashMap();
    HashSet<String> remainingPlayers = new HashSet();
    String playerParticipantId;
    ImmutableArray<Entity> playerS;



    CollisionListener collisionListener;


    private final IPlayService playService;


    public GamePresenter(SugarBeats game, Screen parent) {
        Gdx.app.debug(TAG, "GamePresenter called");

        playService = ServiceLocator.getAppComponent().getNetworkService();
        playService.setNetworkListener(this);

        Gdx.app.debug(TAG, "playService.setNetworkListener(this);");



        this.game = game;
        this.parent = parent;
        engine = new PooledEngine();
        world = new World(engine);
        playerS = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundsComponent.class, TransformComponent.class, StateComponent.class).get());

        view = new GameView(game, this);
//        playService.setNetworkListener(this);

        collisionListener = new CollisionListener() {
            @Override
            public void powerup() {
                System.out.println("Power up sound");
            }

            @Override
            public void ground() {
                System.out.println("Touched the ground!!!");
            }

            @Override
            public void hit() {
                System.out.println("Ouchie got hit..");
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
        engine.addSystem(new NetworkSystem(ServiceLocator.getAppComponent().getNetworkService()));
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

    @Override
    public void onReliableMessageReceived(String senderParticipantId, int describeContents, byte[] messageData) {
        Gdx.app.debug("Sugar Beats GP", "onReliableMessageReceived: " + senderParticipantId + "," + describeContents);
    }

    @Override
    public void onUnreliableMessageReceived(String senderParticipantId, int describeContents, byte[] messageData) {
        engine.getSystem(NetworkSystem.class).processPackage(senderParticipantId, messageData);
    }

    @Override
    public void onRoomReady (List < PlayerData > players) {
     Gdx.app.debug(TAG, "onRoomReady: ");
     addPlayers(players, true);
     //world.initialize();
     }

    public void addPlayers(Collection<PlayerData> data, boolean multiplayer) {
        Gdx.app.debug(TAG, "addPlayers() ");
        players = new HashMap();
        remainingPlayers = new HashSet();

        world.createPlayer(1);
        Gdx.app.debug(TAG, "Player1 CREATED");
        world.createPlayer(2);
        Gdx.app.debug(TAG, "Player2 CREATED");
//        ServiceLocator.getEntityComponent().getDrawableComponentFactory().resetOpponentCount(); MÅ LAGE SPILLERE
        for (PlayerData player : data) {
//            Gdx.app.debug(TAG, player.displayName);
            players.put(player.participantId, player);
            remainingPlayers.add(player.participantId);
            Entity entity;
            if (player.isSelf) {
                Gdx.app.debug(TAG, player.displayName);
                //Må gi meg selv en karakter her
                entity = playerS.get(0);
                playerParticipantId = player.participantId;

                Gdx.app.debug(TAG, "PARTICIPANT ID IF SETNING = " + player.participantId);


            } else {
                Gdx.app.debug(TAG, player.displayName);
                //Definere motstanderen
                entity = playerS.get(1);
                playerParticipantId = player.participantId;
                Gdx.app.debug(TAG, "PARTICIPANT ID ELSE SETNING = " + player.participantId);
            }

            Gdx.app.debug(TAG, "PARTICIPANT ID = " + player.participantId);


        }
    }




}

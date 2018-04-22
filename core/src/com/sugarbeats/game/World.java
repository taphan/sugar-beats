package com.sugarbeats.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.sugarbeats.game.entity.component.AngleComponent;
import com.sugarbeats.game.entity.component.AnimationComponent;
import com.sugarbeats.game.entity.component.BackgroundComponent;
import com.sugarbeats.game.entity.component.BoundsComponent;
import com.sugarbeats.game.entity.component.GravityComponent;
import com.sugarbeats.game.entity.component.GroundComponent;
import com.sugarbeats.game.entity.component.HealthComponent;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.PlayerComponent;
import com.sugarbeats.game.entity.component.ProjectileComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TextureComponent;
import com.sugarbeats.game.entity.component.TransformComponent;
import com.sugarbeats.service.AssetService;

/**
 * Created by Quynh on 4/11/2018.
 *
 * World logic (variables)
 */

public class World {
    public static final float WORLD_WIDTH = 1280;
    public static final float WORLD_HEIGHT = 15 * 20;
    public static final int WORLD_STATE_RUNNING = 0;
    public static final int WORLD_STATE_GAME_OVER = 1;

    public static final Vector2 gravity = new Vector2(0, -9.81f);
    private PooledEngine engine;
    public int state;


    public World (PooledEngine engine) {
        this.engine = engine;
    }

    public void create() {
        createBackground();
        createGround();
        createPlayer(1);

        //createPlayer(2);
        //createPlayer(2);
        //createwalking();


        this.state = WORLD_STATE_RUNNING;
    }

    public void createPlayer(int playerNr){
        Entity entity = engine.createEntity();

        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        GravityComponent gravity = engine.createComponent(GravityComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        HealthComponent health = engine.createComponent(HealthComponent.class);

        animation.animations.put(PlayerComponent.STATE_STANDBY, AssetService.character1);
        animation.animations.put(PlayerComponent.STATE_LEFT, AssetService.walkAnim1);
        animation.animations.put(PlayerComponent.STATE_RIGHT, AssetService.walkAnim1);
        animation.animations.put(PlayerComponent.STATE_HIT, AssetService.getHitAnim1);
        animation.animations.put(PlayerComponent.STATE_SHOOT, AssetService.shootAnim1);
        animation.animations.put(PlayerComponent.STATE_DEATH, AssetService.deathAnim1);

        /*
        movement.music.put(PlayerComponent.STATE_LEFT, AudioService.walkMusic);
        movement.music.put(PlayerComponent.STATE_RIGHT, AudioService.walkMusic);
        movement.music.put(PlayerComponent.STATE_WALK, AudioService.walkMusic);
        */

        bounds.bounds.width = PlayerComponent.WIDTH;
        bounds.bounds.height = PlayerComponent.HEIGHT;

        state.set(PlayerComponent.STATE_STANDBY);

        position.position.add(225.0f,200.0f);
        position.scale.add(-0.9f, -0.9f);

        createAngle(position.position);

        entity.add(animation);
        entity.add(player);
        entity.add(bounds);
        entity.add(gravity);
        entity.add(state);
        entity.add(movement);
        entity.add(position);
        entity.add(texture);
        entity.add(health);

        engine.addEntity(entity);
    }

    public void createProjectile(float x, float y) {
        Entity entity = engine.createEntity();
        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        GravityComponent gravity = engine.createComponent(GravityComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        ProjectileComponent projectile = engine.createComponent(ProjectileComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        animation.animations.put(ProjectileComponent.STATE_START, AssetService.projectileAnim1);
        animation.animations.put(ProjectileComponent.STATE_MIDAIR, AssetService.projectileAnim1);
        animation.animations.put(ProjectileComponent.STATE_HIT, AssetService.projectileAnim1);

        bounds.bounds.width = ProjectileComponent.WIDTH;
        bounds.bounds.height = ProjectileComponent.HEIGHT;

        position.position.add(x,y);
        position.scale.add(-0.9f, -0.9f);

        state.set(ProjectileComponent.STATE_START);

        entity.add(animation);
        entity.add(bounds);
        entity.add(gravity);
        entity.add(state);
        entity.add(movement);
        entity.add(position);
        entity.add(projectile);
        entity.add(texture);

        engine.addEntity(entity);
    }

    private void createAngle(Vector2 position) {
        Entity entity = engine.createEntity();
        StateComponent state = engine.createComponent(StateComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        AngleComponent angle = engine.createComponent(AngleComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        texture.region = AssetService.angle;
        System.out.println(position);
        transform.position.x = position.x;
        transform.position.y = position.y;
        transform.scale.add(-0.8f, -0.8f);

        state.set(AngleComponent.STATE_LEFT);

        entity.add(transform);
        entity.add(state);
        entity.add(movement);
        entity.add(angle);
        entity.add(texture);

        engine.addEntity(entity);
    }

    // If it is possible to choose between several maps, send in an int as a parameter
    private void createGround() {
        Entity entity = engine.createEntity();
        GroundComponent ground = engine.createComponent(GroundComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);

        texture.region = AssetService.map1;
        bounds.bounds.width = texture.region.getRegionWidth();
        bounds.bounds.height = texture.region.getRegionHeight();
        position.position.add(4.0f,0.0f);

        entity.add(ground);
        entity.add(bounds);
        entity.add(position);
        entity.add(texture);

        engine.addEntity(entity);
    }

    //TODO: Scale background
    private void createBackground() {
        Entity entity = engine.createEntity();

        BackgroundComponent background = engine.createComponent(BackgroundComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);

        texture.region = AssetService.background2;

        bounds.bounds.width = texture.region.getRegionWidth();
        bounds.bounds.height = texture.region.getRegionHeight();
        position.position.add(0,0);
        position.scale.add(-0.2f,-0.2f); //NOT GOOD, should be dynamic

        entity.add(background);
        entity.add(position);
        entity.add(texture);
        entity.add(bounds);

        engine.addEntity(entity);
    }

    public void update(float delta) {
        if (state == WORLD_STATE_RUNNING)
            engine.update(delta);
    }
}

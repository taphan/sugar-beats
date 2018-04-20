package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.sugarbeats.game.World;
import com.sugarbeats.game.entity.component.BackgroundComponent;
import com.sugarbeats.game.entity.component.BoundsComponent;
import com.sugarbeats.game.entity.component.GroundComponent;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.PlayerComponent;
import com.sugarbeats.game.entity.component.PowerupComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by Quynh on 4/11/2018.
 *
 * Handle collisions between the players, or if player touched the bound or items.
 */

public class CollisionSystem extends EntitySystem {
    private ComponentMapper<BoundsComponent> bm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;

    public static interface CollisionListener {   // Listens to collisions
        public void powerup();  // Player got powerup
        public void hit();      // Player hit opponent
        public void ground();   // Player touched the ground
    }

    private Engine engine;
    private World world;
    private CollisionListener listener;
    private ImmutableArray<Entity> players;
    private ImmutableArray<Entity> ground;
    private ImmutableArray<Entity> powerups;
    private ImmutableArray<Entity> background;

    public CollisionSystem(World world, CollisionListener listener) {
        this.world = world;
        this.listener = listener;

        bm = ComponentMapper.getFor(BoundsComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;

        players = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundsComponent.class, TransformComponent.class, StateComponent.class).get());
        ground = engine.getEntitiesFor(Family.all(GroundComponent.class, BoundsComponent.class, TransformComponent.class).get());
        powerups = engine.getEntitiesFor(Family.all(PowerupComponent.class, BoundsComponent.class).get());
        background = engine.getEntitiesFor(Family.all(BackgroundComponent.class,BoundsComponent.class,TransformComponent.class ).get());
    }

    @Override
    public void update(float deltaTime) {
        PlayerSystem playerSystem = engine.getSystem(PlayerSystem.class);
        for (int i = 0; i < players.size(); i++) {   // Update all players at each call
            Entity player = players.get(i);
            BoundsComponent playerBounds = bm.get(player);
            TransformComponent playerPosition = tm.get(player);

            // Check if player has been dropped to the ground
            Entity currentGround = ground.get(0);
            BoundsComponent groundBounds = bm.get(currentGround);

            if (playerBounds.bounds.overlaps(groundBounds.bounds)) {
                // If the player is standing on a ground, stop falling
                playerSystem.hitGround(player);
            }

            // Check if player is going out of the map
            Entity currentBackground = background.get(0);
            BoundsComponent backgroundBounds = bm.get(currentBackground);

            if (playerBounds.bounds.overlaps(backgroundBounds.bounds)) {
                // If the player is standing on a ground, stop falling
                playerSystem.hitMapEdge(player);
            }
            /*
            TODO: Må ha projectilecomponent for at man skal kunne bruke denne
            BoundsComponent projectileBounds = bm.get(projectile)

            if (projectileBounds.bounds.overlaps(playerBounds.bounds)){
                playerSystem.getHit(player);
                listener.hit();
            }
            */

            // Check if player touched powerup
            for (int j = 0; j < powerups.size(); j++) {
                Entity powerup = powerups.get(j);
                BoundsComponent powerupBounds = bm.get(powerup);

                if (powerupBounds.bounds.overlaps(playerBounds.bounds)) {  // Check if powerup was touched by each player
                    engine.removeEntity(powerup);  // Player eats powerup & remove it from map
                    listener.powerup();
                    playerSystem.gainPowerup(player,powerup); // Powerup give a state to player
                }
            }

            // TODO: Check if player collided with another player

        }

        // TODO: Check if bullet hit opponent

    }
}

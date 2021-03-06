package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.game.World;
import com.sugarbeats.game.entity.component.HealthComponent;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.PlayerComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TransformComponent;
import com.sugarbeats.service.AudioService;

import static com.sugarbeats.game.entity.component.PlayerComponent.STATE_SHOOT;

/**
 * Created by Quynh on 4/12/2018.
 *
 * Player in the game world logic: Movement, player states (powerup)
 */

public class PlayerSystem extends IteratingSystem {
    // Group together the components that make up this system
    private static final Family family = Family.all(PlayerComponent.class,
            StateComponent.class,
            TransformComponent.class,
            HealthComponent.class,
            MovementComponent.class).get();

    private World world;

    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<HealthComponent> hm;

    private long startTime;

    public PlayerSystem(World world) {
        super(family);

        this.world = world;

        pm = ComponentMapper.getFor(PlayerComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        hm = ComponentMapper.getFor(HealthComponent.class);

    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        StateComponent state = sm.get(entity);
        MovementComponent mov = mm.get(entity);


        if(mov.velocity.x < 0) {
            if (state.get() != PlayerComponent.STATE_LEFT && state.get() != PlayerComponent.STATE_DEATH){
                state.set(PlayerComponent.STATE_LEFT);
                AudioService.playSound(AudioService.walkSound);
            }
        } else if (mov.velocity.x > 0) {
            if (state.get() != PlayerComponent.STATE_RIGHT && state.get() != PlayerComponent.STATE_DEATH){
                state.set(PlayerComponent.STATE_RIGHT);
                AudioService.playSound(AudioService.walkSound);
            }
        } else {
            if (state.get() != PlayerComponent.STATE_STANDBY && state.get() != PlayerComponent.STATE_DEATH
                    && state.get() != STATE_SHOOT && state.get() != PlayerComponent.STATE_HIT){
                state.set(PlayerComponent.STATE_STANDBY);
            }
        }
    }

    public void hitGround(Entity entity) {
        if (!family.matches(entity)) return;
        MovementComponent mov = mm.get(entity);
        mov.velocity.y = 0.0f;
    }


    // Prevent player from going outside of the world's width
    public void hitMapEdge(Entity entity) {
        TransformComponent t = tm.get(entity);

        if (t.position.x < 0) {
            t.position.x = 0;
        }
        if (t.position.x > SugarBeats.WIDTH - PlayerComponent.WIDTH) {
            t.position.x = SugarBeats.WIDTH - PlayerComponent.WIDTH;
        }
    }

    public void fireProjectile(Entity entity) {
        TransformComponent position = tm.get(entity);
        PlayerComponent player = pm.get(entity);
        StateComponent state = sm.get(entity);

        // Player can only shoot another projectile after player.shootDelay milliseconds interval
        player.timeSinceLastShot = TimeUtils.timeSinceMillis(startTime);
        if (player.timeSinceLastShot >= player.shootDelay) {
            startTime = TimeUtils.millis();
            if (!family.matches(entity)) return;
            state.set(STATE_SHOOT);

            world.createProjectile(position.position.x, position.position.y + 30);

            player.timeSinceLastShot = 0;
        }
    }

    public void hitByProjectile(Entity entity) {
        if (!family.matches(entity)) return;

        StateComponent state = sm.get(entity);
        HealthComponent h = hm.get(entity);
        if (state.get() != PlayerComponent.STATE_HIT && state.get() != PlayerComponent.STATE_SHOOT
                && state.get() != PlayerComponent.STATE_DEATH){
            state.set(PlayerComponent.STATE_HIT);
        }
        h.HEALTH -= 1;
        if (h.HEALTH < 0) {
            die(entity);
        }
    }

    public void die(Entity entity) {
        StateComponent state = sm.get(entity);
        state.set(PlayerComponent.STATE_DEATH);
        entity.remove(MovementComponent.class);
        AudioService.playSound(AudioService.deathSound);
    }

    public void standby(Entity entity){
        if (!family.matches(entity)) return;

        StateComponent state = sm.get(entity);
        state.set(PlayerComponent.STATE_STANDBY);
    }

    public Vector2 getPosition(Entity entity) {
        TransformComponent position = tm.get(entity);
        return position.position;
    }
}

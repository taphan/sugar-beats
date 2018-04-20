package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.sugarbeats.SugarBeats;
import com.sugarbeats.game.World;
import com.sugarbeats.game.entity.component.AnimationComponent;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.PlayerComponent;
import com.sugarbeats.game.entity.component.PowerupComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TransformComponent;
import com.sugarbeats.presenter.GamePresenter;

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
            MovementComponent.class,AnimationComponent.class).get();
    // Deleting HealthComponent.class from the family makes it possible to process this system!!

    private World world;

    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<PowerupComponent> pwrm;
    private ComponentMapper<AnimationComponent> am;

    private float velocityX;

    public PlayerSystem(World world) {
        super(family);

        this.world = world;

        pm = ComponentMapper.getFor(PlayerComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);

        velocityX = 0.0f;
    }

    // TODO: Finish all player logics (change states)

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent t = tm.get(entity);
        StateComponent state = sm.get(entity);
        MovementComponent mov = mm.get(entity);
        PlayerComponent player = pm.get(entity);
        AnimationComponent animaton = am.get(entity);

        mov.velocity.x = this.velocityX;
        if(velocityX != 0) {
            if (state.get() != PlayerComponent.STATE_WALK){
                //System.out.println(state.get());
                state.set(PlayerComponent.STATE_WALK);
            }
        } else {
            if (state.get() != PlayerComponent.STATE_STANDBY){
                state.set(PlayerComponent.STATE_STANDBY);
            }
        }
    }

    public void hitGround(Entity entity) {
        StateComponent state = sm.get(entity);
        MovementComponent mov = mm.get(entity);
        mov.velocity.y = 0.0f;
    }

    //TODO: powerup logic
    public void gainPowerup (Entity player, Entity powerup) {
        if (!family.matches(player)) return;

        StateComponent state = sm.get(player);
        PowerupComponent pwr = pwrm.get(player);
        // Set player state to outside of NORMAL?
        // If powerup = SPEED: multiply player's velocity by 1.25

        // If powerup = POWER: multiply player's damage by 1.25
    }

    public void setVelocity(float velocity) {
        this.velocityX = velocity;
    }

    // Prevent player from going outside of the world's width
    public void hitMapEdge(Entity entity) {
        TransformComponent t = tm.get(entity);

        if (t.position.x < 0) {
            t.position.x = 0;
        }
        if (t.position.x > SugarBeats.WIDTH- PlayerComponent.WIDTH) {
            t.position.x = SugarBeats.WIDTH-PlayerComponent.WIDTH;
        }
    }

    public void getHit(Entity entity){
        if (!family.matches(entity)) return;

        StateComponent state = sm.get(entity);
        state.set(PlayerComponent.STATE_HIT);
    }

    public void walking(Entity entity){
        if (!family.matches(entity)) return;

        StateComponent state = sm.get(entity);
        AnimationComponent animation = am.get(entity);

        state.set(PlayerComponent.STATE_PLAY);
    }
}

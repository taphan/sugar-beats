package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.ProjectileComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by taphan on 20.04.2018.
 */

public class ProjectileSystem extends IteratingSystem {
    private static final Family family = Family.all(
            TransformComponent.class,
            MovementComponent.class,
            ProjectileComponent.class,
            StateComponent.class).get();

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<ProjectileComponent> pm;
    private ComponentMapper<StateComponent> sm;

    private Vector2 velocity;

    public ProjectileSystem() {
        super(family);

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        pm = ComponentMapper.getFor(ProjectileComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);

        velocity = new Vector2();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent position = tm.get(entity);
        MovementComponent movement = mm.get(entity);
        ProjectileComponent projectileComponent = pm.get(entity);
        StateComponent stateComponent = sm.get(entity);

        movement.velocity.x += 20*deltaTime*Math.cos(80*Math.PI / 180 ); // v0*t*cos(rad)
        // v0*t*sin(rad) - 1/2*gt
        movement.velocity.y += 20*deltaTime*Math.sin(80*Math.PI / 180 ) - 9.81*deltaTime;
        position.position.add(movement.velocity);

        if(projectileComponent.isDead) {
            stateComponent.set(ProjectileComponent.STATE_HIT);
        }
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}

package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by qaphan on 08.03.2018.
 */

public class MovementSystem extends IteratingSystem{
    private Vector2 velo = new Vector2();

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent position = tm.get(entity);
        MovementComponent movement = mm.get(entity);;

        velo.set(movement.velocity).scl(deltaTime);
        movement.velocity.set(velo);
        //velo.set(movement.velocity).scl(deltaTime);
        position.position.add(velo.x, velo.y, 0.0f);
        velo.set(movement.velocity).scl(1/deltaTime);
        /**
         * velocity.scl(dt);
         position.add(velocity.x, velocity.y);  // Updating the heli's position continuously
         velocity.scl(1/dt);
         */
    }


}

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

        velo.set(movement.acceleration).scl(deltaTime);
        movement.velocity.add(velo);

        velo.set(movement.velocity).scl(deltaTime);
        position.position.add(velo.x, velo.y);
    }


}

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
    private Vector2 tmp = new Vector2();

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent pos = tm.get(entity);
        MovementComponent mov = mm.get(entity);;

        tmp.set(mov.accel).scl(deltaTime);
        mov.velocity.add(tmp);

        tmp.set(mov.velocity).scl(deltaTime);
        pos.pos.add(tmp.x, tmp.y, 0.0f);
    }
/*
    public void addToEngine(Engine engine) {
        entities =  engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    public void update(float deltaTime){
        for (int i = 0; i < entities.size() ; i++) {
            Entity entity = entities.get(i);
            TransformComponent position = tm.get(entity);
            MovementComponent velocity = mm.get(entity);

        position.pos.x += velocity * GameInput.KeyForce.x * deltaTime; // GameInput in managers
        }
    }
*/

}

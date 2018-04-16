package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.sugarbeats.game.World;
import com.sugarbeats.game.entity.component.GravityComponent;
import com.sugarbeats.game.entity.component.MovementComponent;

/**
 * Created by Quynh on 4/12/2018.
 */

public class GravitySystem extends IteratingSystem {
    private ComponentMapper<MovementComponent> mm;

    public GravitySystem() {
        super(Family.all(GravityComponent.class, MovementComponent.class).get());

        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MovementComponent mov = mm.get(entity);
        mov.velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
    }
}
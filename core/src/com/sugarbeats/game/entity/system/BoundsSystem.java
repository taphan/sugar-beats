package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.sugarbeats.game.entity.component.BoundsComponent;
import com.sugarbeats.game.entity.component.TextureComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by Quynh on 4/18/2018.
 */

public class BoundsSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<BoundsComponent> bm;


    public BoundsSystem() {
        super(Family.all(BoundsComponent.class, TransformComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(BoundsComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent position = tm.get(entity);
        BoundsComponent bounds = bm.get(entity);

        bounds.bounds.x = position.position.x - bounds.bounds.width * 0.5f;
        bounds.bounds.y = position.position.y - bounds.bounds.height * 0.5f;
    }
}

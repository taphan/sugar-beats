package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.sugarbeats.game.entity.component.PlayerComponent;
import com.sugarbeats.game.entity.component.PowerupComponent;

/**
 * Created by Quynh on 4/12/2018.
 */

public class PowerupSystem extends IteratingSystem {
    private static final Family family = Family.all(PlayerComponent.class,
            PowerupComponent.class).get();
    private ComponentMapper<PlayerComponent> pm;
    private ComponentMapper<PowerupComponent> pwrm;


    public PowerupSystem(){
        super(family);

        pm = ComponentMapper.getFor(PlayerComponent.class);
        pwrm = ComponentMapper.getFor(PowerupComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // TODO: change player's state according to what powerup they got
    }
}

package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;

/**
 * Created by Quynh on 4/12/2018.
 *
 * Player in the game world logic: Movement, player states (powerup)
 */

public class PlayerSystem extends EntitySystem {


    public void getPowerup (Entity player, Entity state) {

        // If state == powerup.SPEED: multiply player's velocity by 1.25
        // If state == powerup.POWER: multiply player's damage by 1.25
    }

}

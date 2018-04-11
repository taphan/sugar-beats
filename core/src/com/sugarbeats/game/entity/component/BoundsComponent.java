package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Quynh on 4/11/2018.
 *
 * Data containing the bound wrapped around an area (map, player, powerup).
 */

public class BoundsComponent implements Component {
    public final Rectangle bounds = new Rectangle();
}

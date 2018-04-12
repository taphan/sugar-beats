package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by taphan on 08.03.2018.
 */

public class MovementComponent implements Component {
    public final Vector2 velocity = new Vector2();
    public final Vector2 accel = new Vector2();
}

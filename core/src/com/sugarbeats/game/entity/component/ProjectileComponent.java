package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by taphan on 20.04.2018.
 */

public class ProjectileComponent implements Component, Pool.Poolable {
    public static final int STATE_START = 0;
    public static final int STATE_MIDAIR = 1;
    public static final int STATE_HIT = 2;

    public static final float WIDTH = 100f*0.1f;
    public static final float HEIGHT = 100f*0.3f;

    public float xVel = 0;
    public float yVel = 0;
    public boolean isDead = false;

    @Override
    public void reset() {
        xVel = 0;
        yVel = 0;
        isDead = false;
    }
}

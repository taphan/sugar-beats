package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by Quynh on 4/11/2018.
 */

public class PlayerComponent implements Component{
    public static final int STATE_FALL = 0;
    public static final int STATE_HIT = 1;
    public static final int STATE_PLAY = 2;
    public static final int STATE_STANDBY = 3;
    public static final int STATE_NORMAL = 4;
    public static final int STATE_BOOSTED = 5;  // Ate power up
    public static final int STATE_DEATH = 6;
    public static final int STATE_SHOOT = 7;
    public static final int STATE_WALK = 8;

    public static final float WIDTH = 100f*0.18f;
    public static final float HEIGHT = 100f*0.38f;

    public boolean isDead = false;
    public long shootDelay = 1000;
    public long timeSinceLastShot = 0;

}

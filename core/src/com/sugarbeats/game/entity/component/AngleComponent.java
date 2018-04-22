package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by taphan on 22.04.2018.
 */

public class AngleComponent implements Component{
    public static final int STATE_LEFT = 0;
    public static final int STATE_RIGHT = 1;

    public float angle = 0f;
}

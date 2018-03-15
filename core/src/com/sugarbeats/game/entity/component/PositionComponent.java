package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by Quynh on 3/15/2018.
 */

public class PositionComponent implements Component{
    public float x = 0.0f;
    public float y= 0.0f;

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

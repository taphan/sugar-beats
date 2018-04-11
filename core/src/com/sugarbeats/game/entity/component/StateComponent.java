package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by Quynh on 4/11/2018.
 *
 * Game state: READY, RUNNING, OVER
 */

public class StateComponent implements Component {
    private int state = 0;
    public float time = 0.0f;

    public int get() {
        return state;
    }

    public void set(int newState) {
        state = newState;
        time = 0.0f;
    }
}

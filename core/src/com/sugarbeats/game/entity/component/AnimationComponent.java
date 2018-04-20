package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by sirenfj on 12.04.2018.
 */

public class AnimationComponent implements Component,Pool.Poolable {
    public IntMap<Animation> animations = new IntMap<Animation>();

    @Override
    public void reset() {
        animations = null;
    }
}

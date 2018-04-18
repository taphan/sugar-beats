package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
/**
 * Created by sirenfj on 12.04.2018.
 */

public class AnimationComponent implements Component {
    public IntMap<Animation> animations = new IntMap<Animation>();

    public static final int STATE_NORMAL = 1;
}

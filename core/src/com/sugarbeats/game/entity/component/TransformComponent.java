package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Quynh on 4/11/2018.
 *
 * Data of players' positions and ways to change them.
 */

public class TransformComponent implements Component {
    public final Vector2 position = new Vector2();
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
    public float rotation = 0.0f;
}

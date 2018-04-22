package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by taphan on 08.03.2018.
 */

public class MovementComponent implements Component {
    public final Vector2 velocity = new Vector2();
    public final Vector2 acceleration = new Vector2();

    //public IntMap<Music> music = new IntMap<Music>();

}

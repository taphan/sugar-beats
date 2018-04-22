package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.sugarbeats.game.entity.component.AngleComponent;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by taphan on 22.04.2018.
 */

public class AngleSystem extends IteratingSystem {
    private static final Family family = Family.all(
            TransformComponent.class,
            AngleComponent.class,
            StateComponent.class,
            MovementComponent.class).get();

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<AngleComponent> am;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<MovementComponent> mm;

    private Vector2 position;
    private float angle;

    public AngleSystem() {
        super(family);

        tm = ComponentMapper.getFor(TransformComponent.class);
        am = ComponentMapper.getFor(AngleComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);

        position = new Vector2();
        angle = 0f;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movement = mm.get(entity);
        TransformComponent pos = tm.get(entity);

        pos.position.x = this.position.x + 12.5f;
        pos.position.y = this.position.y + 30;
        //pos.rotation = this.angle;
        Vector2 arrowPos = new Vector2(pos.position).setAngle(pos.rotation);
        pos.position.rotate(this.angle);
    }

    // TODO: Call this method when up/down buttons are pressed
    public void updateAngle(float angle) {
        if (angle > 0 && angle < 180)
            this.angle += angle;
    }

    // Move together with the player
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}

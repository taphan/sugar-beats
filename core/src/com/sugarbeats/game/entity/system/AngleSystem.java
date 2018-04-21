package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.sugarbeats.game.entity.component.AngleComponent;
import com.sugarbeats.game.entity.component.StateComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by taphan on 22.04.2018.
 */

public class AngleSystem extends IteratingSystem {
    private static final Family family = Family.all(
            TransformComponent.class,
            AngleComponent.class,
            StateComponent.class).get();

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<AngleComponent> am;
    private ComponentMapper<StateComponent> sm;

    public AngleSystem() {
        super(family);

        tm = ComponentMapper.getFor(TransformComponent.class);
        am = ComponentMapper.getFor(AngleComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    // TODO: Call this method when up/down buttons are pressed
    public void addAngle() {

    }
}

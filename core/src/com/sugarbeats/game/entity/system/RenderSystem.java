package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sugarbeats.game.entity.component.TextureComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by taphan on 08.03.2018.
 */

public class RenderSystem extends IteratingSystem {
    Texture tex;
    private ComponentMapper<TransformComponent> transformM;
    private SpriteBatch batch;


    public RenderSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());
        transformM = ComponentMapper.getFor(TransformComponent.class);
        this.batch = batch;
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        batch.begin();
        tex = new Texture("button_play.png");

        float width = tex.getWidth();
        float height = tex.getHeight();
        float originX = width * 0.5f;
        float originY = height * 0.5f;


        batch.draw(tex, originX, originY);
        batch.end();

    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {


    }
}

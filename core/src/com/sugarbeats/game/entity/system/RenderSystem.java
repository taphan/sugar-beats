package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sugarbeats.game.entity.component.TextureComponent;
import com.sugarbeats.game.entity.component.TransformComponent;

/**
 * Created by taphan on 08.03.2018.
 */

public class RenderSystem extends IteratingSystem {
    Texture tex;
    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<TextureComponent> textureMapper;

    private SpriteBatch batch;


    public RenderSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        textureMapper = ComponentMapper.getFor(TextureComponent.class);

        this.batch = batch;
    }
    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // TODO: skriv alt dette her på en annen måte
        TransformComponent transform = transformMapper.get(entity);
        TextureComponent texture = textureMapper.get(entity);
        TextureRegion region = texture.region;
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        float originX = width * 0.5f;
        float originY = height * 0.5f;
        float x = transform.position.x - originX;
        float y = transform.position.y - originY;

        batch.draw(texture.region, x, y, originX, originY, width, height, transform.scale.x, transform.scale.y,transform.rotation);
    }
}

package com.sugarbeats.game.entity.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.sugarbeats.game.entity.component.MovementComponent;
import com.sugarbeats.game.entity.component.NetworkComponent;
import com.sugarbeats.game.entity.component.PlayerComponent;
import com.sugarbeats.game.entity.component.TransformComponent;
import com.sugarbeats.game.entity.component.iDComponent;
import com.sugarbeats.service.IPlayService;

import java.nio.ByteBuffer;

public class NetworkSystem extends IteratingSystem implements EntityListener {
    private IPlayService playService;

    private static final  Family FAMILY = Family.all(NetworkComponent.class, TransformComponent.class, MovementComponent.class).get();
    private static final byte MOVE = 1;

    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<iDComponent> id;

    private ImmutableArray<Entity> player;
    private ImmutableArray<Entity> syncedEntities;


    public NetworkSystem(IPlayService playService) {
        super(FAMILY);
        this.playService = playService;
        id = ComponentMapper.getFor(iDComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        Gdx.app.log("NETWORK","NetworkSystem initialized.");
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        syncedEntities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
        engine.addEntityListener(Family.all(NetworkListener.class).get(), this);
        player = engine.getEntitiesFor(Family.all(NetworkComponent.class, PlayerComponent.class, iDComponent.class).get());

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        MovementComponent movement = mm.get(entity);
        ByteBuffer buffer = ByteBuffer.allocate(4 * 8 + 1);
        buffer.put(MOVE);
        buffer.putFloat(transform.position.x);
        buffer.putFloat(transform.position.y);
        Gdx.app.log("NETWORK","BUFFER PROCESSED");

        buffer.putFloat(movement.velocity.x);
        buffer.putFloat(movement.velocity.y);
        buffer.putFloat(movement.acceleration.x);
        buffer.putFloat(movement.acceleration.y);
        playService.sendUnreliableMessageToOthers(buffer.array());

    }

    public void processPackage(String playerId, byte[] messageData) {
        ByteBuffer buffer = ByteBuffer.wrap(messageData);
        byte packetType = buffer.get();
        Gdx.app.log("NETWORK","A package was processed.");

        switch (packetType) {
            case MOVE:
                updateEntity(playerId, buffer);
                Gdx.app.log("NETWORK","MOVE was sent through package.");
        }
    }
    private void updateEntity(String participantId, ByteBuffer wrap) {
        Entity entity = null;
        for (Entity syncedEntity : syncedEntities) {
            Gdx.app.log("NETWORK", "An entity was updated.");

            if (id.get(syncedEntity).participantId.equals(participantId)) {
                Gdx.app.log("NETWORK", "updateEntity: found correct ID");

                entity = syncedEntity;
                break;
            }

        }
        if (entity == null) {
            Gdx.app.log("NETWORK", "updateEntity: NULL");
            return;
        }
        TransformComponent transformComponent = tm.get(entity);
        MovementComponent movement = mm.get(entity);
        if (transformComponent == null || movement == null) return;
        transformComponent.position.x = wrap.getFloat();
        transformComponent.position.y = wrap.getFloat();
//        transformComponent.rotation.x = wrap.getFloat();
//        transformComponent.rotation.y = wrap.getFloat();
        movement.velocity.x = wrap.getFloat();
        movement.velocity.y = wrap.getFloat();
        movement.acceleration.x = wrap.getFloat();
        movement.acceleration.y = wrap.getFloat();
    }


    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}

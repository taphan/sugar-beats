package com.sugarbeats.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class iDComponent implements Component, Pool.Poolable{

    public String participantId = "";

    @SuppressWarnings("unused")
    public iDComponent() {
    }

    public iDComponent(String participantId) {
        this.participantId = participantId;
    }


    @Override
    public void reset() {
        participantId = "";
    }
}
